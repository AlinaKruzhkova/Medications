package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstapplication.PlaceholderScreen
import com.example.myfirstapplication.menu.MenuScreenUi

@Composable
fun HomeNavGraph(bottomNavController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomItem.MyProfile.route
    ) {
        composable(BottomItem.MyProfile.route) {
            PlaceholderScreen(BottomItem.MyProfile.title)
        }
        composable(BottomItem.Drug.route) {
            MenuScreenUi {}
        }
        composable(BottomItem.Calendar.route) {
            PlaceholderScreen(BottomItem.Calendar.title)
        }
    }
}