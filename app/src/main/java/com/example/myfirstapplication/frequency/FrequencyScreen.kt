package com.example.myfirstapplication.frequency


import androidx.compose.runtime.Composable
import androidx.navigation.NavController


enum class SelectedOption {
    NONE,
    ONE,
    TWO,
    OWN,
    HARD
}

@Composable
fun FrequencyScreen(navController: NavController) {

    FrequencyContent(
        navigate = {},
        navigateBack = { navController.popBackStack() }
    )
}



