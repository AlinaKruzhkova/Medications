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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
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
import com.example.myfirstapplication.drugchoice.NextButtonUi
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink



enum class SelectedOption {
    NONE,
    ALWAYS,
    DAYS
}

@Composable
fun DatesChoiceUi(navigate: () -> Unit) {
    var selectedOption by remember { mutableStateOf(SelectedOption.NONE) }

    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(12.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
    ) {

        Text(
            text = stringResource(R.string.duration),
            color = DeepBurgundy,
            fontSize = 15.sp, // Размер шрифта для подзаголовка
            modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            lineHeight = 24.sp
        )
        AlwaysButtonUi (
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == SelectedOption.ALWAYS,
            onClick = {
                selectedOption = SelectedOption.ALWAYS
            }
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        DaysButtonUi (
            modifier = Modifier.padding(bottom = 16.dp),
            isSelected = selectedOption == SelectedOption.DAYS,
            onClick = {
                selectedOption = SelectedOption.DAYS
            }
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        NextButtonUi (
            onClick = navigate,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DatesChoicePreview() {
    DatesChoiceUi(navigate = {})
}

