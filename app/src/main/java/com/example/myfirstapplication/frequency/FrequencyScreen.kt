package com.example.myfirstapplication.frequency


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph


enum class FrequencySelectedOption {
    NONE,
    ONE,
    TWO,
    OWN,
    HARD
}

@Composable
fun FrequencyScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(FrequencySelectedOption.NONE) }
    var selectedNumber by remember { mutableIntStateOf(0) }

    FrequencyContent(
        navigate = {
            when (selectedOption) {
                FrequencySelectedOption.ONE,
                FrequencySelectedOption.TWO,
                FrequencySelectedOption.OWN -> navController.navigate(Graph.NOTIFICATION)

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