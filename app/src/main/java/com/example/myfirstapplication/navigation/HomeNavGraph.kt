package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HomeNavGraph(bottomNavController: NavHostController, navController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        route = Graph.HOME,
        startDestination = BottomItem.MyProfile.route
    ) {
        composable(route = BottomItem.MyProfile.route) {
            // TODO: Add Screen
        }
        composable(route = BottomItem.Drug.route) {
            // TODO: Add Screen
        }
        composable(route = BottomItem.Calendar.route) {
            // TODO: Add Screen
        }
    }
}