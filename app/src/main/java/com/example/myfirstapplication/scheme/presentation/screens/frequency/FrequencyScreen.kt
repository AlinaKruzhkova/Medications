package com.example.myfirstapplication.scheme.presentation.screens.frequency


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel


enum class FrequencySelectedOption {
    NONE,
    ONE,
    TWO,
    OWN,
    HARD
}

@Composable
fun FrequencyScreen(navController: NavController) {
    val viewModel = hiltViewModel<SchemeViewModel>()
    var selectedOption by remember { mutableStateOf(FrequencySelectedOption.NONE) }
    var selectedNumber by remember { mutableIntStateOf(0) }

    FrequencyContent(
        navigate = {
            when (selectedOption) {
                FrequencySelectedOption.ONE -> {
                    navController.navigate("${Graph.NOTIFICATION}/1")
                }

                FrequencySelectedOption.TWO -> {
                    viewModel.setDailyIntakesCount(2)
                    navController.navigate("${Graph.NOTIFICATION}/2")
                }

                FrequencySelectedOption.OWN -> {
                    navController.navigate("${Graph.NOTIFICATION}/$selectedNumber")
                }
                FrequencySelectedOption.HARD -> navController.navigate(Graph.HARD_FREQUENCY)
                else -> {}
            }
        },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onSelectedOption = { selectedOption = it },
        selectedNumber = selectedNumber,
        onNumberSelected = { selectedNumber = it }
    )
}