package com.example.myfirstapplication.scheme.domain.model

data class UserDrugScheme(
    val drugId: String? = null,
    val customDrugName: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val numberOfPills: String? = null,
    val lowPillsNumber: String? = null,
    val schedule: Schedule? = null,
    val status: String? = null
)

data class Schedule(
    val intervalInMinutes: String? = null,
    val times: List<String> = emptyList(),
    val daysOfWeek: List<Int> = emptyList()
)