package com.example.myfirstapplication.scheme.presentation.screens.frequency

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.scheme.presentation.screens.dateschoice.buttons.Counter
import com.example.myfirstapplication.scheme.presentation.screens.frequency.buttons.DaysOfWeekSelectorUi
import com.example.myfirstapplication.scheme.presentation.screens.frequency.buttons.NotificationFieldUi
import com.example.myfirstapplication.scheme.presentation.screens.frequency.buttons.SelectableButton
import com.example.myfirstapplication.scheme.presentation.screens.notifications.TimePickerButton
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink
import java.time.LocalTime

@Composable
fun HardFrequencyContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    selectedOption: HardSelectedOption,
    onSelectedOption: (HardSelectedOption) -> Unit,
    selectedDays: List<Int>,
    onSelectedDays: (List<Int>) -> Unit,

    onIntervalChanged: (Int?) -> Unit,
    onStartTimeSelected: (LocalTime?) -> Unit,
    onIntakeCountChanged: (Int?) -> Unit
) {
    var intervalInMinutes by remember { mutableStateOf<Int?>(null) }
    var showStartTime by remember { mutableStateOf(false) }
    var showIntakeCount by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(horizontal = 12.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = navigateBack
            )

            // Заголовок
            Text(
                text = stringResource(R.string.how_often),
                color = DeepBurgundy,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                lineHeight = 24.sp
            )
        }

        // Кнопка "каждые х дней"
        SelectableButton(
            modifier = Modifier.fillMaxWidth(),
            isSelected = selectedOption == HardSelectedOption.INTERVAL,
            onClick = { onSelectedOption(HardSelectedOption.INTERVAL) },
            text = stringResource(R.string.interval)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Если выбрана INTERVAL — показываем NotificationFieldUi()
        AnimatedVisibility(
            visible = selectedOption == HardSelectedOption.INTERVAL,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Column {
                NotificationFieldUi { interval ->
                    intervalInMinutes = interval
                    onIntervalChanged(interval)

                    showStartTime = interval != null && interval < 24 * 60
                    showIntakeCount = interval != null && interval >= 24 * 60
                }
            }
        }

        // Показываем, если пользователь выбрал Интервал в часах
        AnimatedVisibility(visible = showStartTime) {
            Column {
                TimePickerButton(
                    initialTime = LocalTime.now(),
                    onTimeSelected = {
                        onStartTimeSelected(it)
                    }
                )
            }
        }


        // Показываем, если человек выбрал количество дней
        AnimatedVisibility(
            visible = showIntakeCount,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Column {
                Text(
                    text = "Выберите количество приемов в день:",
                    color = DeepBurgundy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = customFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Counter(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onNumberSelected = {
                        onIntakeCountChanged(it)
                    },
                    range = 1..10
                )
            }
        }

        // Кнопка "По дням недели"
        SelectableButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            isSelected = selectedOption == HardSelectedOption.DAYSOFWEEK,
            onClick = { onSelectedOption(HardSelectedOption.DAYSOFWEEK) },
            text = stringResource(R.string.days_of_week)
        )

        // Блок выбора дней недели
        AnimatedVisibility(
            visible = selectedOption == HardSelectedOption.DAYSOFWEEK,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))

                DaysOfWeekSelectorUi(
                    selectedDays = selectedDays,
                    onDayToggle = { index ->
                        val newList = selectedDays.toMutableList()
                        if (newList.contains(index)) {
                            newList.remove(index)
                        } else {
                            newList.add(index)
                        }
                        onSelectedDays(newList)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = selectedOption == HardSelectedOption.DAYSOFWEEK,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Column {
                Text(
                    text = "Выберите количество приемов в день:",
                    color = DeepBurgundy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = customFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Counter(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onNumberSelected = {
                        onIntakeCountChanged(it)
                    },
                    range = 1..10
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val isNextEnabled = when (selectedOption) {
            HardSelectedOption.NONE -> false
            HardSelectedOption.INTERVAL -> true
            HardSelectedOption.DAYSOFWEEK -> selectedDays.isNotEmpty()
        }

        //"Далее"
        NextButton(
            onClick = navigate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            isActive = isNextEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HardFrequencyContentPreview() {
    HardFrequencyContent(
        navigate = {},
        navigateBack = {},
        selectedOption = HardSelectedOption.DAYSOFWEEK,
        onSelectedOption = {},
        selectedDays = listOf(1, 3, 5),
        onSelectedDays = {},
        onIntervalChanged = {},
        onStartTimeSelected = {},
        onIntakeCountChanged = {}
    )
}