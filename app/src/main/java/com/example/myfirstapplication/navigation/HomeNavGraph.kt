package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstapplication.PlaceholderScreen

@Composable
fun HomeNavGraph(bottomNavController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomItem.MyProfile.route
    ) {
        composable(BottomItem.MyProfile.route) {
            PlaceholderScreen("My Profile")
        }
        composable(BottomItem.Drug.route) {
            PlaceholderScreen("Drug Screen")
        }
        composable(BottomItem.Calendar.route) {
            PlaceholderScreen("Calendar")
        }
    }
}