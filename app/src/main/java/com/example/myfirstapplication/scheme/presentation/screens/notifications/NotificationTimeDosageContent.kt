package com.example.myfirstapplication.scheme.presentation.screens.notifications


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.scheme.presentation.screens.dateschoice.buttons.Counter
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun NotificationTimeDosageContent(
    dailyIntakes: Int,
    onDataSelected: (List<Pair<LocalTime, Int>>) -> Unit,
    navigate: () -> Unit,
    navigateBack: () -> Unit
) {
    val intakesData = remember { mutableStateListOf<Pair<LocalTime, Int>>() }

    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    var selectedDosage by remember { mutableStateOf<Int?>(null) }

    var currentStep by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(16.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
    ) {

        // Заголовок
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
                text = stringResource(R.string.when_text),
                color = DeepBurgundy,
                fontSize = 16.sp, // Размер шрифта для подзаголовка
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                lineHeight = 24.sp
            )
        }


        Spacer(modifier = Modifier.height(28.dp))


        // Показываем номер текущего приема
        AnimatedVisibility(
            visible = intakesData.size != dailyIntakes,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = GrayPink,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.intake_number, currentStep, dailyIntakes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBurgundy,
                    fontFamily = customFont
                )
            }
        }


        // Промежуток
        Spacer(modifier = Modifier.height(20.dp))


        // Основной блок выбора
        AnimatedVisibility(
            visible = intakesData.size != dailyIntakes,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000)) + shrinkVertically()
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = GrayPink,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp)
                    .padding(top = 12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    // Кнопка "Назад" (появляется со второго шага)
                    AnimatedVisibility(
                        visible = currentStep > 1,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(
                                onClick = {
                                    // Удаляем последние сохраненные данные и возвращаемся на предыдущий шаг
                                    if (intakesData.isNotEmpty()) {
                                        intakesData.removeAt(intakesData.lastIndex)
                                        currentStep--

                                        val lastData = intakesData.lastOrNull()
                                        if (lastData != null) {
                                            selectedTime = lastData.first
                                            selectedDosage = lastData.second
                                        } else {
                                            selectedTime = LocalTime.now()
                                            selectedDosage = 0
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = DarkBurgundy
                                )
                            }
                        }
                    }


                    TimePickerButton(
                        initialTime = selectedTime,
                        onTimeSelected = { time ->
                            selectedTime = time
                        }
                    )

                    Text(
                        text = stringResource(R.string.dosage),
                        color = DeepBurgundy,
                        fontSize = 16.sp, // Размер шрифта для подзаголовка
                        fontFamily = customFont,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(top = 24.dp)
                    )

                    Counter(
                        modifier = Modifier.padding(bottom = 24.dp),
                        range = 1..10,
                        initialValue = selectedDosage,
                        onNumberSelected = { dosage ->
                            selectedDosage = dosage
                        }
                    )


                    //Кнопка подтверждения выбора
                    val isActiveOkButton = selectedTime != null && selectedDosage != null

                    AnimatedVisibility(
                        visible = isActiveOkButton,
                        enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
                    ) {
                        Button(
                            onClick = {
                                selectedTime?.let { time ->
                                    selectedDosage?.let { dosage ->
                                        intakesData.add(time to dosage)
                                        if (currentStep < dailyIntakes) {
                                            currentStep++
                                            selectedTime = null
                                            selectedDosage = null
                                        } else onDataSelected(intakesData.toList())
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Rose,
                                contentColor = Pink
                            )
                        ) {
                            Text(
                                text = if (currentStep < dailyIntakes) {
                                    stringResource(R.string.next_intake)
                                } else {
                                    stringResource(R.string.finish)
                                },
                                fontFamily = customFont
                            )
                        }
                    }
                }
            }
        }


        AnimatedVisibility(
            visible = intakesData.size == dailyIntakes,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000)) + shrinkVertically()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GrayPink, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.selected_intakes),
                    fontWeight = FontWeight.Bold,
                    color = DeepBurgundy,
                    fontSize = 18.sp,
                    fontFamily = customFont
                )

                Spacer(modifier = Modifier.height(12.dp))

                intakesData.forEachIndexed { index, (time, dosage) ->
                    Text(
                        text = "${index + 1}. ${time.format(DateTimeFormatter.ofPattern("HH:mm"))} — $dosage единиц.",
                        fontSize = 16.sp,
                        color = DarkBurgundy,
                        fontFamily = customFont
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.weight(1f)
        )

        NextButton(
            onClick = navigate,
            isActive = intakesData.size == dailyIntakes,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationTimeDosageContentPreview() {
    NotificationTimeDosageContent(
        dailyIntakes = 5,
        onDataSelected = {},
        navigate = {},
        navigateBack = {}
    )
}