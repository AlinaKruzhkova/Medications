package com.example.myfirstapplication.formchoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel

@Composable
fun FormChoiceScreen(navController: NavController) {
    val viewModel = hiltViewModel<DrugViewModel>()
    val forms by viewModel.forms.collectAsState()

    FormChoiceContent(
        navigate = { navController.navigate(Graph.DRUG_CHOICE) },
        navigateBack = { navController.popBackStack() },
        forms = forms
    )
}

