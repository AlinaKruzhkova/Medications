package com.example.myfirstapplication.calendar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink

@Composable
fun ScheduleUI(
    data: List<Pair<String, String>>
) {
    // Группируем лекарства по времени
    val groupedData = data.groupBy { it.first }.toSortedMap()

    Column(
        modifier = Modifier
            .background(GrayPink, shape = RoundedCornerShape(25.dp))
            .padding(12.dp)
    ) {
        Text(
            text = "Расписание приёма",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))

        if (groupedData.isEmpty()) {
            Text(
                text = "Приём лекарств не запланирован",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            groupedData.forEach { (time, medicines) ->
                ScheduleItem(time, medicines.map { it.second })
            }
        }
    }
}

@Composable
private fun ScheduleItem(
    time: String,
    medicines: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Время с красивым оформлением
        Box(
            modifier = Modifier
                .width(64.dp)
                .background(
                    color = DeepBurgundy.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = DeepBurgundy
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Список лекарств
        Column {
            medicines.forEach { medicine ->
                Text(
                    text = "• $medicine",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}