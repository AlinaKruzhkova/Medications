package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstapplication.login.presentation.LoginScreenInner

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
            HomeScreen()
        }
    }
}

object Graph {
    const val HOME = "home_screen"
    const val LOGIN = "login_screen"
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