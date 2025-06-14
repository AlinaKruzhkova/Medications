package com.example.myfirstapplication.frequency

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
import com.example.myfirstapplication.frequency.buttons.DaysOfWeekSelectorUi
import com.example.myfirstapplication.frequency.buttons.NotificationFieldUi
import com.example.myfirstapplication.frequency.buttons.SelectableButton
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun HardFrequencyContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    selectedOption: HardSelectedOption,
    onSelectedOption: (HardSelectedOption) -> Unit,
    selectedDays: List<Int>,
    onSelectedDays: (List<Int>) -> Unit
) {
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

        // Если выбрана INTERVAL — показываем NotificationFieldUi() с отступом
        if (selectedOption == HardSelectedOption.INTERVAL) {
            Spacer(modifier = Modifier.height(16.dp))
            NotificationFieldUi()
        } else {
            // Если INTERVAL НЕ выбран — просто добавим отступ между кнопками
            Spacer(modifier = Modifier.height(16.dp))
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
        if (selectedOption == HardSelectedOption.DAYSOFWEEK) {
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
        onSelectedDays = {}
    )
}