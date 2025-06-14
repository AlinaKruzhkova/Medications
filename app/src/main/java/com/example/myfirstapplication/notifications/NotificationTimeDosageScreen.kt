package com.example.myfirstapplication.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.myfirstapplication.frequency.FrequencySelectedOption

@Composable
fun NotificationTimeDosageScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(FrequencySelectedOption.NONE) }

    NotificationTimeDosageContent(
        navigate = {},
        navigateBack = { navController.popBackStack() }
    )
}

