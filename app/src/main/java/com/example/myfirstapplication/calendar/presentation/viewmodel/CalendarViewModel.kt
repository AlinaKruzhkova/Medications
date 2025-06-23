package com.example.myfirstapplication.calendar.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.profile.domain.DrugRepository
import com.example.myfirstapplication.pushnotifications.ReminderScheduler
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val schemeRepository: SchemeRepository,
    private val drugRepository: DrugRepository,
    runAsync: RunAsync,
) : BaseViewModel(runAsync) {

    private val _drugMap = MutableStateFlow<Map<String, String>>(emptyMap())

    init {
        runAsync(
            background = {
                drugRepository.getAllDrugs().associate { it.first to it.second.name }
            },
            uiBlock = {
                _drugMap.value = it
            }
        )
    }


    private val today = LocalDate.now()

    private val _weekOffset = mutableIntStateOf(0)
    val weekOffset: Int get() = _weekOffset.value

    private val _selectedDate = MutableStateFlow(today)
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val _scheduleForDay = MutableStateFlow<List<Triple<String, String, Int?>>>(emptyList())
    val scheduleForDay = _scheduleForDay.asStateFlow()


    val mondayThisWeek: LocalDate
        get() = today.minusDays((today.dayOfWeek.value - 1).toLong())

    val currentDays: List<LocalDate>
        get() {
            val startDate = mondayThisWeek.plusDays((weekOffset * 14).toLong())
            return (0..13).map { startDate.plusDays(it.toLong()) }
        }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun changeWeek(offsetChange: Int) {
        _weekOffset.value += offsetChange
    }

    private fun generateTimesForDateFromInterval(
        schedule: Schedule,
        schemeStartDate: String,
        targetDate: LocalDate
    ): List<String>? {
        val startTime = schedule.startTime ?: return null
        val intervalMinutes = schedule.intervalInMinutes ?: return null

        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        // Начальная дата и время
        val startDateTime = LocalDate.parse(schemeStartDate, dateFormatter)
            .atTime(java.time.LocalTime.parse(startTime, timeFormatter))

        val endOfTargetDate = targetDate.plusDays(1).atStartOfDay()

        var current = startDateTime
        val timesForTargetDate = mutableListOf<String>()

        while (current.isBefore(endOfTargetDate)) {
            if (current.toLocalDate() == targetDate) {
                timesForTargetDate.add(current.toLocalTime().format(timeFormatter))
            }
            current = current.plusMinutes(intervalMinutes.toLong())
        }

        return if (timesForTargetDate.isEmpty()) null else timesForTargetDate
    }


    fun loadScheduleForSelectedDate() {
        val date = _selectedDate.value
        val drugsMap = _drugMap.value

        runAsync(
            background = {
                val schemes = schemeRepository.getAllActiveSchemes()
                val results = mutableListOf<Triple<String, String, Int?>>()

                for ((_, scheme) in schemes) {
                    val schedule = scheme.schedule ?: continue

                    val start = scheme.startDate
                    val end = scheme.endDate
                    if (start != null && end != null && !isDateInRange(date, start, end)) continue

                    val dayOfWeek = getDayOfWeek(date)
                    if (schedule.daysOfWeek != null && !schedule.daysOfWeek.contains(dayOfWeek)) continue

                    val drugName = scheme.customDrugName ?: drugsMap[scheme.drugId] ?: "Препарат"

                    val times =
                        schedule.times?.mapNotNull { timeDosage ->
                            val time = timeDosage.time ?: return@mapNotNull null
                            Triple(time, drugName, timeDosage.dosage)
                        }
                            ?: generateTimesForDateFromInterval(
                                schedule,
                                scheme.startDate ?: continue,
                                date
                            )
                                ?.map { time -> Triple(time, drugName, null) }
                            ?: continue

                    results += times
                }

                results.sortedBy { it.first }
            },
            uiBlock = { list ->
                _scheduleForDay.value = list
            }
        )
    }


    private fun isDateInRange(target: LocalDate, start: String, end: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val startDate = LocalDate.parse(start, formatter)
        val endDate = LocalDate.parse(end, formatter)
        return !target.isBefore(startDate) && !target.isAfter(endDate)
    }

    private fun getDayOfWeek(date: LocalDate): Int {
        // Преобразование: ISO -> БД (1 = пн -> 0, ..., 7 = вс -> 6)
        return (date.dayOfWeek.value + 6) % 7
    }


    fun scheduleNotificationsForDate(context: Context, date: LocalDate) {
        val reminderScheduler = ReminderScheduler(context)

        runAsync(
            background = {
                val drugsMap = _drugMap.value
                val schemes = schemeRepository.getAllActiveSchemes()
                val notificationList = mutableListOf<Triple<String, String, Int?>>()

                for ((_, scheme) in schemes) {
                    val schedule = scheme.schedule ?: continue

                    val start = scheme.startDate
                    val end = scheme.endDate
                    if (start != null && end != null && !isDateInRange(date, start, end)) continue

                    val dayOfWeek = getDayOfWeek(date)
                    if (schedule.daysOfWeek != null && !schedule.daysOfWeek.contains(dayOfWeek)) continue

                    val drugName = scheme.customDrugName ?: drugsMap[scheme.drugId] ?: "Препарат"

                    val times = schedule.times?.mapNotNull { td ->
                        td.time?.let { Triple(it, drugName, td.dosage) }
                    } ?: generateTimesForDateFromInterval(
                        schedule,
                        scheme.startDate ?: continue,
                        date
                    )
                        ?.map { Triple(it, drugName, null) }
                    ?: continue

                    notificationList += times
                }

                notificationList
            },
            uiBlock = { times ->
                times.forEachIndexed { index, (timeStr, drugName, dosage) ->
                    val formatter = DateTimeFormatter.ofPattern("HH:mm")
                    val localTime = LocalTime.parse(timeStr, formatter)
                    val dateTime = date.atTime(localTime)

                    val msg = if (dosage != null)
                        "Примите $drugName: $dosage ед."
                    else
                        "Пора принять $drugName"

                    reminderScheduler.scheduleReminder(
                        dateTime = dateTime,
                        title = "Напоминание",
                        message = msg,
                        requestCode = (date.toString() + timeStr).hashCode() + index
                    )
                }
            }
        )
    }

    fun scheduleAllNotifications(context: Context) {
        val reminderScheduler = ReminderScheduler(context)

        runAsync(
            background = {
                val drugsMap = _drugMap.value
                val schemes = schemeRepository.getAllActiveSchemes()
                val allNotifications = mutableListOf<Triple<LocalDateTime, String, Int>>()

                val dateFormatter = DateTimeFormatter.ofPattern("HH:mm")

                val today = LocalDate.now()
                val daysAhead = 14 // Можно увеличить по желанию

                for (offset in 0 until daysAhead) {
                    val date = today.plusDays(offset.toLong())

                    for ((_, scheme) in schemes) {
                        val schedule = scheme.schedule ?: continue

                        val start = scheme.startDate
                        val end = scheme.endDate
                        if (start != null && end != null && !isDateInRange(
                                date,
                                start,
                                end
                            )
                        ) continue

                        val dayOfWeek = getDayOfWeek(date)
                        if (schedule.daysOfWeek != null && !schedule.daysOfWeek.contains(dayOfWeek)) continue

                        val drugName =
                            scheme.customDrugName ?: drugsMap[scheme.drugId] ?: "Препарат"

                        val times = schedule.times?.mapNotNull { td ->
                            td.time?.let { Triple(it, drugName, td.dosage) }
                        } ?: generateTimesForDateFromInterval(
                            schedule,
                            scheme.startDate ?: continue,
                            date
                        )
                            ?.map { Triple(it, drugName, null) }
                        ?: continue

                        times.forEachIndexed { index, (timeStr, name, dosage) ->
                            val localTime = LocalTime.parse(timeStr, dateFormatter)
                            val dateTime = date.atTime(localTime)
                            val message =
                                dosage?.let { "Примите $name: $it ед." } ?: "Пора принять $name"

                            allNotifications.add(
                                Triple(
                                    dateTime,
                                    message,
                                    (date.toString() + timeStr).hashCode() + index
                                )
                            )
                        }
                    }
                }

                allNotifications
            },
            uiBlock = { notifications ->
                notifications.forEach { (dateTime, message, requestCode) ->
                    reminderScheduler.scheduleReminder(
                        dateTime = dateTime,
                        title = "Напоминание",
                        message = message,
                        requestCode = requestCode
                    )
                }
            }
        )
    }
}