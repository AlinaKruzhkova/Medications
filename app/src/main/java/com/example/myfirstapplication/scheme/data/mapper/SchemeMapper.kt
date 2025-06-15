package com.example.myfirstapplication.scheme.data.mapper

import com.example.myfirstapplication.scheme.domain.model.Schedule
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import javax.inject.Inject

class SchemeMapper @Inject constructor() {
    fun map(
        selectedDrugId: String?,
        query: String,
        startDate: String,
        endDate: String,
        numberOfPills: String,
        lowPillsNumber: String,
        schedule: Schedule
    ): UserDrugScheme {
        val isCustom = selectedDrugId.isNullOrEmpty()
        return UserDrugScheme(
            drugId = selectedDrugId ?: "",
            customDrugName = if (isCustom) query else "",
            startDate = startDate,
            endDate = endDate,
            numberOfPills = numberOfPills,
            lowPillsNumber = lowPillsNumber,
            schedule = schedule,
            status = "active"
        )
    }
}
