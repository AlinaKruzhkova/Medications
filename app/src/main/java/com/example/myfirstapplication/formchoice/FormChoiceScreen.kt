package com.example.myfirstapplication.formchoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel

@Composable
fun FormChoiceScreen(navController: NavController) {
    val viewModel = hiltViewModel<DrugViewModel>()
    val forms by viewModel.forms.collectAsState()
    val query by viewModel.searchQuery.collectAsState()
    var selectedForm by rememberSaveable { mutableStateOf<String?>(null) }
    FormChoiceContent(
        navigate = {
            val chosenForm = selectedForm ?: query
            navController.navigate(Graph.DRUG_CHOICE)
        },
        navigateBack = { navController.popBackStack() },
        forms = forms,
        query = query,
        onQueryChanged = {
            viewModel.onSearchQueryChanged(it)
            selectedForm = null // сбрасываем выбранный препарат при новом вводе
        },
        selectedForm = selectedForm,
        onFormSelected = { selectedForm = it },
    )
}

