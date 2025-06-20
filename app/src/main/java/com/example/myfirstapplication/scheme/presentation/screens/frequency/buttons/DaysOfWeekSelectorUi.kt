package com.example.myfirstapplication.scheme.presentation.screens.frequency.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.ui.theme.Burgundy
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.DarkGreen
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Rose

@Composable
fun DaysOfWeekSelectorUi(
    modifier: Modifier = Modifier,
    selectedDays: List<Int>,
    onDayToggle: (Int) -> Unit
) {
    val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Row(
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(color = Burgundy.copy(alpha = 0.16f), shape = RoundedCornerShape(25.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        days.forEachIndexed { index, day ->
            val isSelected = selectedDays.contains(index)

            Box(
                modifier = Modifier
                    .height(70.dp)
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) Green else Color.Transparent)
                    .clickable { onDayToggle(index) },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = day,
                        color = if (isSelected) DarkBurgundy else Rose,
                        fontSize = 14.sp
                    )
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = DarkGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DaysOfWeekSelectorPreview() {
    var selectedDays by remember { mutableStateOf(listOf<Int>()) }

    DaysOfWeekSelectorUi(
        selectedDays = selectedDays,
        onDayToggle = { index ->
            selectedDays = if (selectedDays.contains(index)) {
                selectedDays - index
            } else {
                selectedDays + index
            }
        }
    )
}