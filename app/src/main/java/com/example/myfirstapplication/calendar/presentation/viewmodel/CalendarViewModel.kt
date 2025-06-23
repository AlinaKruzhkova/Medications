package com.example.myfirstapplication.calendar.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.profile.domain.DrugRepository
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
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

    private val _scheduleForDay = MutableStateFlow<List<Pair<String, String>>>(emptyList())
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
                val results = mutableListOf<Pair<String, String>>()

                for ((_, scheme) in schemes) {
                    val schedule = scheme.schedule ?: continue

                    val start = scheme.startDate
                    val end = scheme.endDate
                    if (start != null && end != null && !isDateInRange(date, start, end)) continue

                    val dayOfWeek = getDayOfWeek(date)
                    if (schedule.daysOfWeek != null && !schedule.daysOfWeek.contains(dayOfWeek)) continue

                    val drugName = scheme.customDrugName ?: drugsMap[scheme.drugId] ?: "Препарат"

                    val times =
                        schedule.times?.mapNotNull { it.time?.let { time -> time to drugName } }
                            ?: generateTimesForDateFromInterval(
                                schedule,
                                scheme.startDate ?: continue,
                                date
                            )
                                ?.map { it to drugName }
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

}