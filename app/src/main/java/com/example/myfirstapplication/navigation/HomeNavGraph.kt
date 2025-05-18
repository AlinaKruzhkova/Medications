package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstapplication.calendar.presentation.CalendarScreenUi
import com.example.myfirstapplication.menu.MenuScreenUi
import com.example.myfirstapplication.profile.presentation.ProfileScreenUI

@Composable
fun HomeNavGraph(bottomNavController: NavHostController, navController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomItem.Drug.route
    ) {
        composable(BottomItem.MyProfile.route) {
            ProfileScreenUI(navController)
        }
        composable(BottomItem.Drug.route) {
            MenuScreenUi {}
        }
        composable(BottomItem.Calendar.route) {
            CalendarScreenUi(navController)
        }
    }
}