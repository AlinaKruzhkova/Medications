package com.example.myfirstapplication.scheme.data.mapper

import com.example.myfirstapplication.scheme.domain.model.Schedule
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import javax.inject.Inject

class SchemeMapper @Inject constructor() {
    fun map(
        selectedDrugId: String?,
        query: String?,
        startDate: String?,
        endDate: String?,
        numberOfPills: String?,
        lowPillsNumber: String?,
        schedule: Schedule?
    ): UserDrugScheme {
        return UserDrugScheme(
            drugId = selectedDrugId,
            customDrugName = if (selectedDrugId.isNullOrEmpty()) query else null,
            startDate = startDate,
            endDate = endDate,
            numberOfPills = numberOfPills,
            lowPillsNumber = lowPillsNumber,
            schedule = schedule,
            status = when {
                schedule != null -> "active"
                numberOfPills != null -> "pills_selected"
                startDate != null -> "dates_selected"
                selectedDrugId != null || !query.isNullOrEmpty() -> "drug_selected"
                else -> "unfinished"
            }
        )
    }
}
