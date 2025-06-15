package com.example.myfirstapplication.frequency

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.myfirstapplication.frequency.buttons.SelectableButton
import com.example.myfirstapplication.scheme.presentation.dateschoice.buttons.Counter
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun FrequencyContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    selectedOption: FrequencySelectedOption,
    onSelectedOption: (FrequencySelectedOption) -> Unit,
    selectedNumber: Int,
    onNumberSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(16.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
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

            Text(
                text = stringResource(R.string.how_often),
                color = DeepBurgundy,
                fontSize = 16.sp, // Размер шрифта для подзаголовка
                modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
                textAlign = TextAlign.Center, // Выравнивание текста по центру
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                lineHeight = 24.sp
            )
        }

        SelectableButton(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == FrequencySelectedOption.ONE,
            onClick = { onSelectedOption(FrequencySelectedOption.ONE) },
            text = stringResource(R.string.one_button)
        )
        SelectableButton(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == FrequencySelectedOption.TWO,
            onClick = { onSelectedOption(FrequencySelectedOption.TWO) },
            text = stringResource(R.string.two_button)
        )
        SelectableButton(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == FrequencySelectedOption.OWN,
            onClick = { onSelectedOption(FrequencySelectedOption.OWN) },
            text = stringResource(R.string.own_button)
        )
        SelectableButton(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == FrequencySelectedOption.HARD,
            onClick = { onSelectedOption(FrequencySelectedOption.HARD) },
            text = stringResource(R.string.hard_scheme_button)
        )
        AnimatedVisibility(
            visible = selectedOption == FrequencySelectedOption.OWN,
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
                    onNumberSelected = onNumberSelected,
                    range = 1..10
                )
            }
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        val isNextActive = when (selectedOption) {
            FrequencySelectedOption.NONE -> false
            FrequencySelectedOption.OWN -> selectedNumber > 0
            else -> true
        }
        NextButton(
            onClick = navigate,
            modifier = Modifier.padding(bottom = 16.dp),
            isActive = isNextActive
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FrequencyContentPreview() {
    FrequencyContent(
        navigate = {},
        navigateBack = {},
        selectedOption = FrequencySelectedOption.ONE,
        onSelectedOption = {},
        selectedNumber = 5,
        onNumberSelected = {}
    )
}