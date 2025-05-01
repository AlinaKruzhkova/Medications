package com.example.myfirstapplication.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.White
import kotlinx.coroutines.delay

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
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 }
        ) + fadeIn(animationSpec = tween(durationMillis = 1000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            DrugAndDays(drugName, dosageInfo, onDelete)
            PillsLeftCard(pillsLeft)
        }
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
                    tint = DarkBurgundy
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
            text = getPillsLeftText(pillsLeft),
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = customFont
        )
    }
}

fun getPillsLeftText(count: Int): String {
    val noun = when {
        count % 100 in 11..14 -> "таблеток"
        count % 10 == 1 -> "таблетка"
        count % 10 in 2..4 -> "таблетки"
        else -> "таблеток"
    }

    val verb = if (count % 10 == 1 && count % 100 != 11) "Осталась" else "Осталось"

    return "$verb $count $noun"
}



@Preview(showBackground = true, backgroundColor = 123134645574)
@Composable
fun DrugCardUiPreview() {
    DrugCardUi(
        drugName = "Абактал",
        dosageInfo = "Ежедневно",
        pillsLeft = 121,
        onDelete = { /* handle delete */ }
    )
}