package com.example.myfirstapplication.frequency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController


enum class HardSelectedOption {
    NONE,
    INTERVAL,
    DAYSOFWEEK
}

@Composable
fun HardFrequencyScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(HardSelectedOption.NONE) }
    var selectedDays = remember { mutableStateListOf<Int>() }

    HardFrequencyContent(
        navigate = {},
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onSelectedOption = { selectedOption = it },
        selectedDays = selectedDays,
        onSelectedDays = {
            selectedDays.clear()
            selectedDays.addAll(it)
        }
    )
}