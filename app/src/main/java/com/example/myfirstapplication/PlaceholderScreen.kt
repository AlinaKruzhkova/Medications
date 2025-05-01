package com.example.myfirstapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.common.DrugCardUi
import com.example.myfirstapplication.common.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun PlaceholderScreen(title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = customFont,
                color = DeepBurgundy
            ),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrugCardUi("Абакал", "Ежедневно", 12, {})
    }
}