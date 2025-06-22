package com.example.myfirstapplication.menu.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myfirstapplication.menu.presentation.viewmodel.MenuViewModel
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel

@Composable
fun MenuScreen(navController: NavController) {
    val viewModel = hiltViewModel<MenuViewModel>()
    val drugViewModel = hiltViewModel<DrugViewModel>()

    val drugs by drugViewModel.drugs.collectAsState()
    val schemesState by viewModel.schemesState.collectAsStateWithLifecycle()

    MenuContent(
        navigate = {
            navController.navigate(Graph.FORM_CHOICE)
        },
        itemsList = schemesState,
        drugs = drugs
    )
}