package com.example.myfirstapplication.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfirstapplication.formchoice.FormChoiceScreen
import com.example.myfirstapplication.login.presentation.LoginScreenInner
import com.example.myfirstapplication.profile.presentation.deletedschemes.DeletedSchemesScreen
import com.example.myfirstapplication.profile.presentation.druglist.DrugListScreen
import com.example.myfirstapplication.scheme.presentation.screens.dateschoice.DatesChoiceScreen
import com.example.myfirstapplication.scheme.presentation.screens.drugchoice.DrugChoiceScreen
import com.example.myfirstapplication.scheme.presentation.screens.frequency.FrequencyScreen
import com.example.myfirstapplication.scheme.presentation.screens.frequency.HardFrequencyScreen
import com.example.myfirstapplication.scheme.presentation.screens.notifications.NotificationTimeDosageScreen
import com.example.myfirstapplication.scheme.presentation.screens.restock.RestockNotificationScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.LOGIN,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            ) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            ) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            ) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            ) + fadeOut()
        }
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
        composable(
            route = "${Graph.NOTIFICATION}/{count}",
            arguments = listOf(navArgument("count") { type = NavType.IntType })
        ) {
            NotificationTimeDosageScreen(navController, it.arguments?.getInt("count") ?: 0)
        }
        composable(route = Graph.HARD_FREQUENCY) {
            HardFrequencyScreen(navController)
        }
        composable(route = Graph.RESTOCK) {
            RestockNotificationScreen(navController)
        }
        composable(route = Graph.DELETED_SHEMES) {
            DeletedSchemesScreen(navController)
        }
    }
}

object Graph {
    const val DELETED_SHEMES = "deleted_scheme_screen"
    const val HOME = "home_screen"
    const val LOGIN = "login_screen"
    const val DRUG_LIST = "drug_list_screen"
    const val DRUG_CHOICE = "drug_choice_screen"
    const val FORM_CHOICE = "form_choice_screen"
    const val DATES_CHOICE = "dates_choice_screen"
    const val FREQUENCY = "frequency_screen"
    const val HARD_FREQUENCY = "hard_frequency_screen"
    const val NOTIFICATION = "notification_screen"
    const val RESTOCK = "restock_screen"
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