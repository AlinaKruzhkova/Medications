package com.example.myfirstapplication.scheme.presentation.screens.frequency

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import java.time.LocalTime


enum class HardSelectedOption {
    NONE,
    INTERVAL,
    DAYSOFWEEK
}

@Composable
fun HardFrequencyScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(HardSelectedOption.NONE) }
    var selectedDays = remember { mutableStateListOf<Int>() }

    var intervalInMinutes by remember { mutableStateOf<Int?>(null) }
    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var intakeCountPerDay by remember { mutableStateOf<Int?>(null) }

    HardFrequencyContent(
        navigate = {
            // Передача данных — можно сохранить или передать в ViewModel
            Log.d("DEBUG", "SelectedOption: $selectedOption")
            Log.d("DEBUG", "IntervalMinutes: $intervalInMinutes")
            Log.d("DEBUG", "StartTime: $startTime")
            Log.d("DEBUG", "SelectedDaysOfWeek: $selectedDays")
            Log.d("DEBUG", "IntakeCountPerDay: $intakeCountPerDay")

            navController.navigate(Graph.HOME)
        },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onSelectedOption = { selectedOption = it },
        selectedDays = selectedDays,
        onSelectedDays = {
            selectedDays.clear()
            selectedDays.addAll(it)
        },
        onIntervalChanged = { intervalInMinutes = it },
        onStartTimeSelected = { startTime = it },
        onIntakeCountChanged = { intakeCountPerDay = it }
    )
}