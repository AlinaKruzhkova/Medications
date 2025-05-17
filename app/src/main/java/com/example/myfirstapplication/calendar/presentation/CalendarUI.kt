package com.example.myfirstapplication.calendar.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
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
import kotlin.math.abs
import kotlin.math.sign


@Composable
fun CalendarUI(onDateSelected: (LocalDate) -> Unit) {
    val today = remember { LocalDate.now() }
    var selectedDate by remember { mutableStateOf(today) }
    var weekOffset by remember { mutableIntStateOf(0) }

    val mondayThisWeek = remember(today) { today.minusDays((today.dayOfWeek.value - 1).toLong()) }
    val startDate = mondayThisWeek.plusDays((weekOffset * 14).toLong())
    val days = (0..13).map { startDate.plusDays(it.toLong()) }

    Column(
        modifier = Modifier
            .background(GrayPink, shape = RoundedCornerShape(25))
            .padding(12.dp)
            .pointerInput(weekOffset) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (abs(dragAmount) > 50) { // простейшая защита от лёгкого свайпа
                        weekOffset -= dragAmount.sign.toInt()
                    }
                }
            }
    ) {

        MonthAndYearRow(selectedDate)

        androidx.compose.animation.AnimatedContent(
            targetState = days,
            label = "AnimatedCalendarBlock",
            transitionSpec = {
                (slideInHorizontally(
                    animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                ) { it } + fadeIn(
                    animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                )).togetherWith(
                    slideOutHorizontally(
                        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                    ) { -it } + fadeOut(
                        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                    )
                )
            }

        ) { animDays ->
            Column {
                animDays.chunked(7).forEach { week ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    ) {
                        week.forEach { date ->
                            DayItem(
                                date = date,
                                isSelected = date == selectedDate,
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
    }
}


@Composable
fun DayItem(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val isFirstDay = date.dayOfMonth == 1

    val animatedBgColor by animateColorAsState(
        targetValue = when {
            isSelected -> LightGreen
            isFirstDay && !isSelected -> Pink
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = 350),
        label = "bgColor"
    )

    val animatedTextColor by animateColorAsState(
        targetValue = when {
            isSelected -> Pink
            else -> DeepBurgundy
        },
        animationSpec = tween(durationMillis = 350),
        label = "textColor"
    )

    val dayOfMonth = date.dayOfMonth.toString()
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ru"))

    Column(
        modifier = Modifier
            .width(40.dp)
            .padding(4.dp)
            .background(animatedBgColor, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(dayOfMonth, color = animatedTextColor, fontSize = 16.sp)
        Text(dayOfWeek.take(2), color = if (isSelected) Pink else Rose, fontSize = 12.sp)
        Dot(isSelected)
    }
}

@Composable
fun Dot(visible: Boolean) {
    val dotSize = remember { Animatable(0f) }

    LaunchedEffect(visible) {
        if (visible) {
            dotSize.animateTo(
                targetValue = 5f,
                animationSpec = tween(durationMillis = 700, delayMillis = 200)
            )
        } else {
            dotSize.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 700, delayMillis = 200)
            )
        }
    }

    if (dotSize.value > 0f) {
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .size(dotSize.value.dp)
                .background(Rose, shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MonthAndYearRow(date: LocalDate) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        AnimatedContent(
            targetState = date,
            transitionSpec = {
                fadeIn(tween(400)) with fadeOut(tween(100))
            },
            label = "MonthAndYearTransition"
        ) { targetDate ->
            Text(
                text = targetDate.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
                    .replaceFirstChar { it.uppercase() } + " ${targetDate.year}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(12.dp)
            )
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
    CalendarUI {}
}