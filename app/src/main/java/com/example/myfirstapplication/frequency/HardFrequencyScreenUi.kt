package com.example.myfirstapplication.frequency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink


enum class HardSelectedOption {
    NONE,
    INTERVAL,
    DAYSOFWEEK
}

@Composable
fun HardFrequencyScreenUi(navigate: () -> Unit) {
    var selectedOption by remember { mutableStateOf(HardSelectedOption.NONE) }
    val selectedDays = remember { mutableStateListOf<Int>() }

    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(horizontal = 12.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        // Кнопка "каждые х дней"
        IntervalButtonUi(
            modifier = Modifier
                .fillMaxWidth(),
            isSelected = selectedOption == HardSelectedOption.INTERVAL,
            onClick = { selectedOption = HardSelectedOption.INTERVAL }
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
        DaysOfWeekButtonUi(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            isSelected = selectedOption == HardSelectedOption.DAYSOFWEEK,
            onClick = { selectedOption = HardSelectedOption.DAYSOFWEEK }
        )

        // Блок выбора дней недели
        if (selectedOption == HardSelectedOption.DAYSOFWEEK) {
            Spacer(modifier = Modifier.height(16.dp))

            DaysOfWeekSelectorUi(
                selectedDays = selectedDays,
                onDayToggle = { index ->
                    if (selectedDays.contains(index)) {
                        selectedDays.remove(index)
                    } else {
                        selectedDays.add(index)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        //"Далее"
        NextButton(
            onClick = navigate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HardFrequencyScreenPreview() {
    HardFrequencyScreenUi(navigate = {})
}

