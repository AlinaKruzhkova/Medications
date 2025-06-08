package com.example.myfirstapplication.restock

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
import com.example.myfirstapplication.ui.theme.Green
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
fun RestockFields() {
    var currentAmount by remember { mutableStateOf("30") }
    var thresholdAmount by remember { mutableStateOf("10") }

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
                value = currentAmount,
                onValueChange = { currentAmount = it }
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
                value = thresholdAmount,
                onValueChange = { thresholdAmount = it }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RestockFieldsPreview() {
    MaterialTheme {
        RestockFields()
    }
}

























//@Composable
//fun StockReminderSection() {
//    var currentAmount by remember { mutableStateOf("30") }
//    var thresholdAmount by remember { mutableStateOf("10") }
//
//    val unitText = "единиц"
//
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(16.dp)) {
//
//        Text(
//            text = "Текущие запасы:",
//            fontSize = 14.sp,
//            color = DeepBurgundy
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//
//        OutlinedTextField(
//            value = currentAmount,
//            onValueChange = { if (it.all { ch -> ch.isDigit() }) currentAmount = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Количество") },
//            trailingIcon = {
//                Text(
//                    text = "$unitText",
//                    fontSize = 14.sp,
//                    color = DarkGreen,
//                    modifier = Modifier.padding(end = 8.dp)
//                )
//            },
//            singleLine = true,
//            shape = RoundedCornerShape(8.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "Напомнить мне, когда:",
//            fontSize = 14.sp,
//            color = DeepBurgundy
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//
//        OutlinedTextField(
//            value = thresholdAmount,
//            onValueChange = { if (it.all { ch -> ch.isDigit() }) thresholdAmount = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Осталось") },
//            trailingIcon = {
//                Text(
//                    text = "$unitText",
//                    fontSize = 14.sp,
//                    color = DarkGreen,
//                    modifier = Modifier.padding(end = 8.dp)
//                )
//            },
//            singleLine = true,
//            shape = RoundedCornerShape(8.dp)
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun StockReminderPreview() {
//    StockReminderSection()
//}