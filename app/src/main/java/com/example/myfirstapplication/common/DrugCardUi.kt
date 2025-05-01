package com.example.myfirstapplication.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.White

val customFont = FontFamily(
    Font(R.font.rubik_one_regular)
)

@Composable
fun DrugCardUi(
    drugName: String,
    dosageInfo: String,
    pillsLeft: Int,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Pink)
            .padding(8.dp)
    ) {
        DrugAndDays(drugName, dosageInfo, onDelete)
        PillsLeftCard(pillsLeft)
    }
}

@Composable
fun DrugAndDays(
    drugName: String,
    dosageInfo: String,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(32.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = drugName,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = customFont,
                        color = DeepBurgundy
                    )
                )
                Text(
                    text = dosageInfo,
                    fontSize = 14.sp,
                    color = DeepBurgundy
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete",
                    tint = Color(0xFF89395B)
                )
            }
        }
    }
}

@Composable
fun PillsLeftCard(pillsLeft: Int) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp)
            .background(color = Green, shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Осталось $pillsLeft таблеток",
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = customFont
        )
    }
}

@Preview(showBackground = true, backgroundColor = 123134645574)
@Composable
fun DrugCardUiPreview() {
    DrugCardUi(
        drugName = "Абактал",
        dosageInfo = "Ежедневно",
        pillsLeft = 30,
        onDelete = { /* handle delete */ }
    )
}