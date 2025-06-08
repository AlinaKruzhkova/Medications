package com.example.myfirstapplication.dateschoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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


@Composable
fun CounterScreenUi(navigate: () -> Unit) {

    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(12.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
    ) {
        Text(
            text = stringResource(R.string.counter_screen),
            color = DeepBurgundy,
            fontSize = 16.sp, // Размер шрифта для подзаголовка
            modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        Counter(
                modifier = Modifier.padding(bottom = 16.dp),
                //range = 3..10,   это мне понадобится во втором счетчике
                onNumberSelected = { }
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(
            onClick = navigate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    CounterScreenUi(navigate = {})
}
