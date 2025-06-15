package com.example.myfirstapplication.notifications

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
import com.example.myfirstapplication.scheme.presentation.dateschoice.buttons.Counter
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun NotificationTimeDosageContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit
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

            // Заголовок
            Text(
                text = stringResource(R.string.when_text),
                color = DeepBurgundy,
                fontSize = 16.sp, // Размер шрифта для подзаголовка
                modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
                textAlign = TextAlign.Center, // Выравнивание текста по центру
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                lineHeight = 24.sp
            )
        }
        TimePickerUi()
        Text(
            text = stringResource(R.string.dosage),
            color = DeepBurgundy,
            fontSize = 16.sp, // Размер шрифта для подзаголовка
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            fontFamily = customFont,
            lineHeight = 24.sp
        )
        Counter(
            modifier = Modifier.padding(bottom = 16.dp),
            range = 3..10,
            onNumberSelected = { }
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

@Preview(showBackground = true)
@Composable
fun NotificationTimeDosageContentPreview() {
    NotificationTimeDosageContent(
        navigate = {},
        navigateBack = {}
    )
}