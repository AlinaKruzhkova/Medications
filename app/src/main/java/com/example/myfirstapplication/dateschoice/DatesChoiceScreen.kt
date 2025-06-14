package com.example.myfirstapplication.dateschoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph

enum class SelectedOption {
    NONE,
    ALWAYS,
    DAYS
}

@Composable
fun DatesChoiceScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(SelectedOption.NONE) }
    var selectedNumber by remember { mutableIntStateOf(0) }

    DatesChoiceContent(
        navigate = { navController.navigate(Graph.FREQUENCY) },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        selectedNumber = selectedNumber,
        onNumberSelected = { selectedNumber = it }
    )
}