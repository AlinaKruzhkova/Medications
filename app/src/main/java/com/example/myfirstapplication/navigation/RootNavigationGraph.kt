package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstapplication.dateschoice.DatesChoiceScreen
import com.example.myfirstapplication.drugchoice.DrugChoiceScreen
import com.example.myfirstapplication.formchoice.FormChoiceScreen
import com.example.myfirstapplication.frequency.FrequencyScreen
import com.example.myfirstapplication.frequency.HardFrequencyScreen
import com.example.myfirstapplication.login.presentation.LoginScreenInner
import com.example.myfirstapplication.notifications.NotificationTimeDosageScreen
import com.example.myfirstapplication.profile.presentation.druglist.DrugListScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.LOGIN
    ) {
        composable(route = Graph.LOGIN) {
            LoginScreen(navController)
        }
        composable(route = Graph.HOME) {
            HomeScreen(navController)
        }
        composable(route = Graph.DRUG_LIST) {
            DrugListScreen(navController)
        }
        composable(route = Graph.DRUG_CHOICE) {
            DrugChoiceScreen(navController)
        }
        composable(route = Graph.DATES_CHOICE) {
            DatesChoiceScreen(navController)
        }
        composable(route = Graph.FORM_CHOICE) {
            FormChoiceScreen(navController)
        }
        composable(route = Graph.FREQUENCY) {
            FrequencyScreen(navController)
        }
        composable(route = Graph.NOTIFICATION) {
            NotificationTimeDosageScreen(navController)
        }
        composable(route = Graph.HARD_FREQUENCY) {
            HardFrequencyScreen(navController)
        }
    }
}

object Graph {
    const val HOME = "home_screen"
    const val LOGIN = "login_screen"
    const val DRUG_LIST = "drug_list_screen"
    const val DRUG_CHOICE = "drug_choice_screen"
    const val FORM_CHOICE = "form_choice_screen"
    const val DATES_CHOICE = "dates_choice_screen"
    const val FREQUENCY = "frequency_screen"
    const val HARD_FREQUENCY = "hard_frequency_screen"
    const val NOTIFICATION = "notification_screen"
}

@Composable
fun LoginScreen(navHostController: NavHostController) {
    LoginScreenInner {
        navHostController.navigate(Graph.HOME) {
            popUpTo(Graph.LOGIN) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}