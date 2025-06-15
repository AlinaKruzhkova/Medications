package com.example.myfirstapplication.scheme.presentation.dateschoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.calendar.presentation.CalendarUI
import com.example.myfirstapplication.calendar.presentation.viewmodel.CalendarViewModel
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class DaysSelectedOption {
    NONE,
    ALWAYS,
    DAYS
}

@Composable
fun DatesChoiceScreen(navController: NavController) {
    val schemeViewModel = hiltViewModel<SchemeViewModel>()
    val calendarViewModel = hiltViewModel<CalendarViewModel>()
    val scope = rememberCoroutineScope()

    var selectedOption by remember { mutableStateOf(DaysSelectedOption.NONE) }
    var selectedNumber by remember { mutableIntStateOf(0) }
    val selectedDate by calendarViewModel.selectedDate.collectAsState()
    val currentScheme by schemeViewModel.currentScheme.collectAsState()

    DatesChoiceContent(
        navigate = {
            scope.launch {
                // Сохраняем даты перед переходом
                schemeViewModel.saveDates(
                    selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    daysCount = if (selectedOption == DaysSelectedOption.DAYS) selectedNumber else null
                )
                navController.navigate(Graph.FREQUENCY)
            }
        },
        navigateBack = { navController.popBackStack() },
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        selectedNumber = selectedNumber,
        onNumberSelected = { selectedNumber = it },
        calendar = {
            CalendarUI(
                selectedDate = selectedDate,
                onDateSelected = calendarViewModel::selectDate,
                weekOffset = calendarViewModel.weekOffset,
                onSwipeWeekChange = calendarViewModel::changeWeek,
                minSelectableDate = LocalDate.now()
            )
        }
    )
}