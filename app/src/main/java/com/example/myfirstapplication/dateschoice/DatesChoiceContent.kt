package com.example.myfirstapplication.dateschoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DatesChoiceContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    selectedOption: SelectedOption,
    onOptionSelected: (SelectedOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(12.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
    ) {
        BackButton(
            onClick = navigateBack
        )

        Text(
            text = stringResource(R.string.duration),
            color = DeepBurgundy,
            fontSize = 16.sp, // Размер шрифта для подзаголовка
            modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            lineHeight = 24.sp
        )
        AlwaysButtonUi(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == SelectedOption.ALWAYS,
            onClick = { onOptionSelected(SelectedOption.ALWAYS) }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        DaysButtonUi(
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == SelectedOption.DAYS,
            onClick = { onOptionSelected(SelectedOption.DAYS) }
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        NextButton(
            onClick = navigate,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}