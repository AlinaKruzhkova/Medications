package com.example.myfirstapplication.profile.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel = hiltViewModel<ProfileViewModel>()

    ProfileContent(
        user = viewModel.userInfo(),
        logout = {
            viewModel.logout()
            navController.navigate(Graph.LOGIN) {
                popUpTo(Graph.HOME) { inclusive = true }
            }
        },
        navigateToDrugListScreen = {
            navController.navigate(Graph.DRUG_LIST)
        }
    )

}