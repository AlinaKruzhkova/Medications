package com.example.myfirstapplication.scheme.presentation.screens.restock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.ui.theme.DarkGreen
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun CustomRestockField(
    label: String,
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .width(60.dp)
            )

            // текст справа
            Text(
                text = "единиц",
                color = DeepBurgundy,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun RestockFields(
    onDataChanged: (Int?, Int?) -> Unit
) {
    var numberOfPillsInput by remember { mutableStateOf("30") }
    var lowNumberOfPillsInput by remember { mutableStateOf("10") }


    // Валидируем только если строка не пустая
    val numberOfPills = numberOfPillsInput.toIntOrNull()
    val lowNumberOfPills = lowNumberOfPillsInput.toIntOrNull()


    // Сообщаем наверх
    LaunchedEffect(numberOfPillsInput, lowNumberOfPillsInput) {
        onDataChanged(numberOfPills, lowNumberOfPills)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = "Текущие запасы:",
                color = DeepBurgundy,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            CustomRestockField(
                label = "Количество",
                value = numberOfPillsInput,
                onValueChange = { numberOfPillsInput = it }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Column {
            Text(
                text = "Напомнить мне, когда:",
                color = DeepBurgundy,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            CustomRestockField(
                label = "Осталось",
                value = lowNumberOfPillsInput,
                onValueChange = { lowNumberOfPillsInput = it }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RestockFieldsPreview() {
    MaterialTheme {
        RestockFields(
            onDataChanged = { _, _ -> }
        )
    }
}

