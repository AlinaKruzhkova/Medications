package com.example.myfirstapplication.dateschoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

enum class SelectedOption {
    NONE,
    ALWAYS,
    DAYS
}

@Composable
fun DatesChoiceScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(SelectedOption.NONE) }

    DatesChoiceContent(
        navigate = { },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it }
    )
}