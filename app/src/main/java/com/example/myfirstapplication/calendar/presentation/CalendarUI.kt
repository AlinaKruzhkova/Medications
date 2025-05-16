package com.example.myfirstapplication.calendar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink
import com.example.myfirstapplication.ui.theme.LightGreen
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarUI(onDateSelected: (LocalDate) -> Unit) {
    val today = remember { LocalDate.now() }
    var selectedDate by remember { mutableStateOf(today) }

    val startDate = today.minusDays(9)
    val days = (0..13).map { startDate.plusDays(it.toLong()) }

    Column(
        modifier = Modifier
            .background(GrayPink, shape = RoundedCornerShape(25))
            .padding(12.dp)
    ) {
        days.chunked(7).forEach { week ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                week.forEach { date ->
                    val isSelected = date == selectedDate
                    DayItem(
                        date = date,
                        isSelected = isSelected,
                        onClick = {
                            selectedDate = date
                            onDateSelected(date)
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun DayItem(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val dayOfMonth = date.dayOfMonth.toString()
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ru"))

    val bgColor = if (isSelected) LightGreen else Color.Transparent
    val textColor = if (isSelected) Pink else DeepBurgundy

    Column(
        modifier = Modifier
            .width(40.dp)
            .padding(4.dp)
            .background(bgColor, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(dayOfMonth, color = textColor, fontSize = 16.sp)
        Text(dayOfWeek.take(2), color = if (isSelected) Pink else Rose, fontSize = 12.sp)
        if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(Rose, shape = RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayItemPreview() {
    DayItem(
        date = LocalDate.now(),
        isSelected = true,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun CalendarUiPreview() {
    CalendarUI({})
}