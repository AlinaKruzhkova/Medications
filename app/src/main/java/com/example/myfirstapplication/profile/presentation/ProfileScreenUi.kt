package com.example.myfirstapplication.profile.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph

@Composable
fun ProfileScreenUI(navController: NavController) {

    val viewModel = hiltViewModel<ProfileViewModel>()


    ProfileContent(
        user = viewModel.userInfo(),
        logout = {
            viewModel.logout()
            navController.navigate(Graph.LOGIN) {
                popUpTo(Graph.HOME) { inclusive = true }
            }
        },
    )

}