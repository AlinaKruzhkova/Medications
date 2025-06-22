package com.example.myfirstapplication.scheme.presentation.screens.frequency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime


enum class HardSelectedOption {
    NONE,
    INTERVAL,
    DAYS_OF_WEEK
}

@Composable
fun HardFrequencyScreen(navController: NavController) {
    val viewModel = hiltViewModel<SchemeViewModel>()

    var selectedOption by remember { mutableStateOf(HardSelectedOption.NONE) }
    var selectedDays = remember { mutableStateListOf<Int>() }

    var intervalInMinutes by remember { mutableStateOf<Int?>(null) }
    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var intakeCountPerDay by remember { mutableStateOf<Int?>(null) }

    val scope = rememberCoroutineScope()

    HardFrequencyContent(
        navigate = {

            scope.launch {
                viewModel.saveSchedule(
                    times = null,
                    daysOfWeek = selectedDays,
                    intervalInMinutes = intervalInMinutes,
                    startTime = startTime
                )
            }

            when {
                selectedOption == HardSelectedOption.NONE -> {}
                selectedOption == HardSelectedOption.INTERVAL && intervalInMinutes != null && intervalInMinutes!! < 24 * 60 -> navController.navigate(
                    Graph.RESTOCK
                )

                selectedOption == HardSelectedOption.INTERVAL && intervalInMinutes != null && intervalInMinutes!! >= 24 * 60 -> navController.navigate(
                    "${Graph.NOTIFICATION}/$intakeCountPerDay"
                )

                selectedOption == HardSelectedOption.DAYS_OF_WEEK -> navController.navigate("${Graph.NOTIFICATION}/$intakeCountPerDay")
            }
        },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onSelectedOption = {
            selectedOption = it

            when (it) {
                HardSelectedOption.INTERVAL -> {
                    selectedDays.clear()
                    intakeCountPerDay = null
                    startTime = null // если он есть, сбрасываем до выбора заново
                }

                HardSelectedOption.DAYS_OF_WEEK -> {
                    intervalInMinutes = null
                    startTime = null
                }

                else -> {}
            }
        },
        selectedDays = selectedDays,
        onSelectedDays = {
            selectedDays.clear()
            selectedDays.addAll(it)
        },
        onIntervalChanged = {
            intervalInMinutes = it

            if (it != null && it < 24 * 60) {
                intakeCountPerDay = null // Не показывать счетчик, если показываем время
            } else if (it != null && it >= 24 * 60) {
                startTime = null // Убираем время, если переключились на дни
            }
        },
        onStartTimeSelected = { startTime = it },
        onIntakeCountChanged = { intakeCountPerDay = it },
        showCountPerDay = selectedOption == HardSelectedOption.INTERVAL && intervalInMinutes != null && intervalInMinutes!! >= 24 * 60,
        showStartTime = selectedOption == HardSelectedOption.INTERVAL && intervalInMinutes != null && intervalInMinutes!! < 24 * 60
    )
}