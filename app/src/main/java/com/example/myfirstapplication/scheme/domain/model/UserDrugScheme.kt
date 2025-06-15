package com.example.myfirstapplication.scheme.domain.model

data class UserDrugScheme(
    val drugId: String = "",
    val customDrugName: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val numberOfPills: String = "",
    val lowPillsNumber: String = "",
    val schedule: Schedule = Schedule(),
    val status: String = ""
)

data class Schedule(
    val intervalInMinutes: String = "",
    val times: List<String> = emptyList(),
    val daysOfWeek: List<Int> = emptyList()
)