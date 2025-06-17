package com.example.myfirstapplication.scheme.presentation.screens.notifications


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.scheme.domain.model.TimeDosage
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter


@Composable
fun NotificationTimeDosageScreen(
    navController: NavController,
    dailyIntakes: Int
) {
    val schemeViewModel = hiltViewModel<SchemeViewModel>()
    val scope = rememberCoroutineScope()

    NotificationTimeDosageContent(
        dailyIntakes = dailyIntakes,
        onDataSelected = { data ->
            val converted = data.map { (time, dosage) ->
                TimeDosage(time.format(DateTimeFormatter.ofPattern("HH:mm")), dosage)
            }

            scope.launch {
                schemeViewModel.saveSchedule(converted)
            }
        },
        navigate = {
            navController.navigate(Graph.RESTOCK)
        },
        navigateBack = {
            navController.popBackStack()
        }
    )
}