package com.example.myfirstapplication.scheme.domain.model

data class UserDrugScheme(
    val drugId: String? = null,
    val customDrugName: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val numberOfPills: Int? = null,
    val lowPillsNumber: Int? = null,
    val isNotificationForPillsEnabled: Boolean? = null,
    val schedule: Schedule? = null,
    val status: String? = null
)

data class Schedule(
    val intervalInMinutes: Int? = null,
    val times: List<TimeDosage>? = null,
    val daysOfWeek: List<Int>? = null
)

data class TimeDosage(
    val time: String? = null,
    val dosage: Int? = null
)