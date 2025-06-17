package com.example.myfirstapplication.scheme.presentation.screens.frequency.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.ui.theme.DarkGreen
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun Field(
    label: String,
    suffix: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Pink, shape = RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = DeepBurgundy,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // текст слева
            Text(
                text = label,
                color = DeepBurgundy,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            // Поле ввода (по центру)
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) onValueChange(newValue)
                },
                textStyle = TextStyle(
                    color = DarkGreen,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                modifier = Modifier
                    .width(60.dp)
            )

            // текст справа
            Text(
                text = suffix,
                color = DeepBurgundy,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun NotificationFieldUi() {
    var hours by remember { mutableStateOf("6") }
    var days by remember { mutableStateOf("5") }

        Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Field(
            label = "Напоминать каждые",
            suffix = "часов",
            value = hours,
            onValueChange = { hours = it }
        )
        Field(
            label = "Напоминать каждые",
            suffix = "дней",
            value = days,
            onValueChange = { days = it }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationFieldUiPreview() {
    MaterialTheme {
        NotificationFieldUi()
    }
}
