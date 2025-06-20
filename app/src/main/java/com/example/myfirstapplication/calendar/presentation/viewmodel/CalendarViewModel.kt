package com.example.myfirstapplication.calendar.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    runAsync: RunAsync,
) : BaseViewModel(runAsync) {
    private val today = LocalDate.now()

    private val _weekOffset = mutableIntStateOf(0)
    val weekOffset: Int get() = _weekOffset.value

    private val _selectedDate = MutableStateFlow(today)
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    val mondayThisWeek: LocalDate
        get() = today.minusDays((today.dayOfWeek.value - 1).toLong())

    val currentDays: List<LocalDate>
        get() {
            val startDate = mondayThisWeek.plusDays((weekOffset * 14).toLong())
            return (0..13).map { startDate.plusDays(it.toLong()) }
        }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun changeWeek(offsetChange: Int) {
        _weekOffset.value += offsetChange
    }
}