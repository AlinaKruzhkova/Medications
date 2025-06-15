package com.example.myfirstapplication.scheme.domain.model

data class PartialDrugScheme(
    val selectedDrugId: String? = null,
    val customDrugName: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val numberOfPills: String = "",
    val lowPillsNumber: String = "",
    val schedule: Schedule? = null,
    val status: String = "unfinished"
)