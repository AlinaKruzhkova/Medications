package com.example.myfirstapplication.drugchoice

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
fun DrugChoiceScreen(navController: NavController) {
    val viewModel = hiltViewModel<DrugViewModel>()
    val drugs by viewModel.drugs.collectAsState()
    val query by viewModel.searchQuery.collectAsState()
    var selectedDrug by rememberSaveable { mutableStateOf<String?>(null) }

    DrugChoiceContent(
        navigate = {
            val chosenDrug = selectedDrug ?: query
            // Тут можешь сохранить в ViewModel или передать дальше
            navController.navigate(Graph.DATES_CHOICE)
        },
        navigateBack = { navController.popBackStack() },
        drugs = drugs,
        query = query,
        onQueryChanged = {
            viewModel.onSearchQueryChanged(it)
            selectedDrug = null // сбрасываем выбранный препарат при новом вводе
        },
        selectedDrug = selectedDrug,
        onDrugSelected = { selectedDrug = it },
    )
}