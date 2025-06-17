package com.example.myfirstapplication.scheme.presentation.screens.notifications


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NotificationTimeDosageScreen(
    navController: NavController,
    dailyIntakes: Int
) {

    val viewModel = hiltViewModel<SchemeViewModel>()
    LaunchedEffect(Unit) {
        viewModel.setDailyIntakesCount(dailyIntakes)
    }

    val dailyIntakes by viewModel.dailyIntakesCount.collectAsState()
    val intakesData = remember { mutableStateListOf<Pair<LocalTime, Int>>() }
    var currentStep by remember { mutableIntStateOf(1) }


    NotificationTimeDosageContent(
        dailyIntakes = dailyIntakes,
        currentStep = currentStep,
        intakesData = intakesData,
        onTimeSelected = { time ->
            // Данные сохраняем только после выбора дозы
            if (currentStep <= dailyIntakes) {
                intakesData.add(Pair(time, 0)) // Дозу установим позже
            }
        },
        onDosageSelected = { dosage ->
            val index = currentStep - 1
            if (index in intakesData.indices) {
                intakesData[index] = intakesData[index].copy(second = dosage)
                if (currentStep < dailyIntakes) {
                    currentStep++
                }
            }
        },
        navigate = {
            // TODO: можно сохранить данные, перейти дальше
        },
        navigateBack = {
            if (currentStep > 1) {
                currentStep--
                intakesData.removeLast()
            } else {
                navController.popBackStack()
            }
        }
    )
}

