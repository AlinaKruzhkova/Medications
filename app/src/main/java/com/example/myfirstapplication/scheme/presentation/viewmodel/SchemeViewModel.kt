package com.example.myfirstapplication.scheme.presentation.viewmodel

import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.scheme.data.mapper.SchemeMapper
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.Schedule
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SchemeViewModel @Inject constructor(
    private val repository: SchemeRepository,
    private val schemeMapper: SchemeMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _currentScheme = MutableStateFlow(UserDrugScheme())
    val currentScheme = _currentScheme.asStateFlow()

    init {
        loadCurrentScheme()
    }

    private fun loadCurrentScheme() {
        runAsync(
            background = {
                // Пытаемся загрузить текущий schemeId
                val currentId = repository.getCurrentSchemeId()
                if (currentId != null) {
                    // Если есть сохраненный schemeId, загружаем схему
                    val schemes = repository.getUserSchemes()
                    schemes.firstOrNull { it.first == currentId }?.second?.let { scheme ->
                        _currentScheme.value = scheme
                    }
                }
            },
            uiBlock = {}
        )
    }

    var schemeId: String? = null

    suspend fun saveDrugSelection(drugId: String?, customName: String?) {
        _currentScheme.update {
            it.copy(
                drugId = if (customName != null) null else drugId,
                customDrugName = if (drugId != null) null else customName,
                status = "unfinished"
            )
        }
        savePartialUpdates()
    }

    suspend fun saveDates(startDate: String, daysCount: Int?) {
        val endDate = daysCount?.let { calculateEndDate(startDate, it) }

        _currentScheme.update { current ->
            current.copy(
                startDate = startDate,
                endDate = endDate,
                status = "dates_selected"
            )
        }
        savePartialUpdates()
    }

    fun updatePillInfo(count: String, lowLimit: String) {
        _currentScheme.update { current ->
            current.copy(
                numberOfPills = count,
                lowPillsNumber = lowLimit
            )
        }
    }

    fun updateSchedule(schedule: Schedule) {
        _currentScheme.update { current ->
            current.copy(
                schedule = schedule,
                status = "active"
            )
        }
    }

    suspend fun finalize() {
        _currentScheme.update { it.copy(status = "active") }
        savePartialUpdates()
        repository.clearCurrentSchemeId()
    }

    private suspend fun savePartialUpdates() {
        val currentId = repository.getCurrentSchemeId()
        if (currentId == null) {
            // Если schemeId нет, создаем новую схему
            val newId = repository.addUserScheme(_currentScheme.value)
            repository.setCurrentSchemeId(newId)
        } else {
            // Если schemeId есть, обновляем существующую
            val updates = _currentScheme.value.toUpdateMap()
            repository.updatePartialScheme(currentId, updates)
        }
    }

    fun UserDrugScheme.toUpdateMap(): Map<String, Any?> = mapOf(
        "drugId" to drugId,
        "customDrugName" to customDrugName,
        "startDate" to startDate,
        "endDate" to endDate,
        "numberOfPills" to numberOfPills,
        "lowPillsNumber" to lowPillsNumber,
        "schedule" to schedule,
        "status" to status
    )

    private fun calculateEndDate(startDate: String, daysCount: Int): String {
        return try {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance().apply {
                time = dateFormat.parse(startDate) ?: return startDate
                add(Calendar.DAY_OF_YEAR, daysCount - 1) // -1 потому что включаем первый день
            }
            dateFormat.format(calendar.time)
        } catch (e: Exception) {
            startDate // в случае ошибки возвращаем startDate
        }
    }

    // количество приемов в день (промежуточное значение)
    private val _dailyIntakesCount = MutableStateFlow(0)
    val dailyIntakesCount = _dailyIntakesCount.asStateFlow()

    fun setDailyIntakesCount(count: Int) {
        _dailyIntakesCount.value = count
    }
}