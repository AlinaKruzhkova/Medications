package com.example.myfirstapplication.scheme.presentation.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink
import java.time.LocalTime

@Composable
fun NotificationTimeDosageContent(
    dailyIntakes: Int,
    currentStep: Int,
    intakesData: List<Pair<LocalTime, Int>>,
    onTimeSelected: (LocalTime) -> Unit,
    onDosageSelected: (Int) -> Unit,
    navigate: () -> Unit,
    navigateBack: () -> Unit
) {
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    var selectedDosage by remember { mutableIntStateOf(0) }

    val isOkButtonEnabled = selectedDosage > 0

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

        Spacer(modifier = Modifier.height(24.dp))

        // Показываем номер текущего приема
        Text(
            text = stringResource(R.string.intake_number, currentStep, dailyIntakes),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DeepBurgundy,
            modifier = Modifier.padding(bottom = 8.dp),
            fontFamily = customFont
        )

        TimePicker(
            initialTime = selectedTime,
            onTimeSelected = { time ->
                selectedTime = time
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

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
            range = 1..10,
            onNumberSelected = { dosage ->
                selectedDosage = dosage
            }
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = if (currentStep < dailyIntakes) stringResource(R.string.next_intake)
            else stringResource(R.string.finish),
            fontFamily = customFont
        )
        NextButton(
            onClick = {
                onTimeSelected(selectedTime)
                onDosageSelected(selectedDosage)

                if (currentStep == dailyIntakes) {
                    navigate()
                }
                selectedDosage = 0
            },
            isActive = isOkButtonEnabled,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationTimeDosageContentPreview() {
    NotificationTimeDosageContent(
        navigate = {},
        navigateBack = {},
        dailyIntakes = TODO(),
        currentStep = TODO(),
        intakesData = TODO(),
        onTimeSelected = TODO(),
        onDosageSelected = TODO()
    )
}