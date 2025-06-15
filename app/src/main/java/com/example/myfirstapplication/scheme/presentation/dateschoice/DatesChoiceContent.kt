package com.example.myfirstapplication.scheme.presentation.dateschoice

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.scheme.presentation.dateschoice.buttons.AlwaysButtonUi
import com.example.myfirstapplication.scheme.presentation.dateschoice.buttons.Counter
import com.example.myfirstapplication.scheme.presentation.dateschoice.buttons.DaysButtonUi
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DatesChoiceContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    selectedOption: DaysSelectedOption,
    onOptionSelected: (DaysSelectedOption) -> Unit,
    selectedNumber: Int,
    onNumberSelected: (Int) -> Unit,
    calendar: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(16.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onClick = navigateBack)

            Text(
                text = stringResource(R.string.duration),
                color = DeepBurgundy,
                fontSize = 18.sp, // Размер шрифта для подзаголовка
                textAlign = TextAlign.Center, // Выравнивание текста по центру
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                lineHeight = 24.sp
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        AlwaysButtonUi(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == DaysSelectedOption.ALWAYS,
            onClick = { onOptionSelected(DaysSelectedOption.ALWAYS) }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        DaysButtonUi(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == DaysSelectedOption.DAYS,
            onClick = { onOptionSelected(DaysSelectedOption.DAYS) }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        AnimatedVisibility(
            visible = selectedOption == DaysSelectedOption.DAYS,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Column {
                Text(
                    text = stringResource(R.string.counter_screen),
                    color = DeepBurgundy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = customFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Counter(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onNumberSelected = onNumberSelected
                )

                Text(
                    text = stringResource(R.string.start_date),
                    color = DeepBurgundy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = customFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                calendar()
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        val isNextActive = when (selectedOption) {
            DaysSelectedOption.ALWAYS -> true
            DaysSelectedOption.DAYS -> selectedNumber > 0
            DaysSelectedOption.NONE -> false
        }

        NextButton(
            onClick = navigate,
            modifier = Modifier.padding(bottom = 16.dp),
            isActive = isNextActive
        )
    }
}