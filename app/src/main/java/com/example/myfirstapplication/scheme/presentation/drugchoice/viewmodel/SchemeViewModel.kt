package com.example.myfirstapplication.scheme.presentation.drugchoice.viewmodel

import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.scheme.data.mapper.SchemeMapper
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.PartialDrugScheme
import com.example.myfirstapplication.scheme.domain.model.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SchemeViewModel @Inject constructor(
    private val repository: SchemeRepository,
    private val schemeMapper: SchemeMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _partialScheme = MutableStateFlow(PartialDrugScheme())
    val partialScheme = _partialScheme.asStateFlow()

    private var currentSchemeId: String? = null

    fun saveDrugSelection(drugId: String?, customName: String) {
        _partialScheme.update {
            it.copy(
                selectedDrugId = drugId,
                customDrugName = customName,
                status = if (drugId != null || customName.isNotEmpty()) "drug_selected" else "unfinished"
            )
        }

        val status =
            if (drugId != null) "drug_selected" else if (customName.isNotBlank()) "custom_drug_entered" else "unfinished"

        val updates = mapOf<String, Any>(
            "drugId" to (drugId ?: ""),
            "customDrugName" to (if (drugId == null) customName else ""),
            "status" to status
        )


        savePartialUpdates(updates)
    }

    suspend fun saveDates(start: String, end: String) {
        _partialScheme.update {
            it.copy(
                startDate = start,
                endDate = end,
                status = "dates_selected"
            )
        }

        val updates = mutableMapOf(
            "startDate" to start,
            "status" to "dates_selected"
        )
        end?.let { updates["endDate"] = it }

        savePartialUpdates(updates)
    }

    fun updatePillInfo(count: String, lowLimit: String) {
        _partialScheme.update { it.copy(numberOfPills = count, lowPillsNumber = lowLimit) }
    }

    fun updateSchedule(schedule: Schedule) {
        _partialScheme.update { it.copy(schedule = schedule) }
    }

    suspend fun finalizeAndSave() {
        val data = partialScheme.value
        val mapped = schemeMapper.map(
            selectedDrugId = data.selectedDrugId,
            query = data.customDrugName,
            startDate = data.startDate,
            endDate = data.endDate,
            numberOfPills = data.numberOfPills,
            lowPillsNumber = data.lowPillsNumber,
            schedule = data.schedule ?: error("Schedule is not set")
        )

        repository.addUserScheme(mapped)
    }

    // Удали suspend
    private fun savePartialUpdates(updates: Map<String, Any>) {
        runAsync(
            background = {
                if (currentSchemeId == null) {
                    currentSchemeId = repository.addUserScheme(
                        schemeMapper.map(
                            selectedDrugId = _partialScheme.value.selectedDrugId,
                            query = _partialScheme.value.customDrugName,
                            startDate = _partialScheme.value.startDate,
                            endDate = _partialScheme.value.endDate,
                            numberOfPills = _partialScheme.value.numberOfPills,
                            lowPillsNumber = _partialScheme.value.lowPillsNumber,
                            schedule = _partialScheme.value.schedule ?: Schedule()
                        )
                    )
                } else {
                    repository.updatePartialScheme(currentSchemeId!!, updates)
                }
            },
            uiBlock = {}
        )
    }
}