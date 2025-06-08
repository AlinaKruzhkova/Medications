package com.example.myfirstapplication.calendar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myfirstapplication.calendar.presentation.viewmodel.CalendarViewModel
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun CalendarScreenUi(navController: NavHostController) {
    val viewModel = hiltViewModel<CalendarViewModel>()
    val selectedDate by viewModel.selectedDate

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(vertical = 50.dp, horizontal = 4.dp)
    ) {
        CalendarUI(
            selectedDate = selectedDate,
            onDateSelected = viewModel::selectDate,
            weekOffset = viewModel.weekOffset,
            onSwipeWeekChange = viewModel::changeWeek,
        )
    }
}