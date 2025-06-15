package com.example.myfirstapplication.scheme.presentation.drugchoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel
import com.example.myfirstapplication.scheme.presentation.drugchoice.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch

@Composable
fun DrugChoiceScreen(navController: NavController) {
    // viewModel
    val drugViewModel = hiltViewModel<DrugViewModel>()
    val schemeViewModel = hiltViewModel<SchemeViewModel>()

    val drugs by drugViewModel.drugs.collectAsState()
    val searchQuery by drugViewModel.searchQuery.collectAsState()
    val partialScheme by schemeViewModel.partialScheme.collectAsState()

    val selectedDrugId = partialScheme.selectedDrugId
    val customDrugName = partialScheme.customDrugName
    val scope = rememberCoroutineScope()

    DrugChoiceContent(
        searchQuery = searchQuery,
        onSearchQueryChanged = {
            drugViewModel.onSearchQueryChanged(it)
            if (selectedDrugId != null) {
                schemeViewModel.saveDrugSelection(drugId = null, customName = it)
            }
        },
        drugs = drugs,
        selectedDrugId = selectedDrugId,
        selectedDrugName = drugs.firstOrNull { it.first == selectedDrugId }?.second?.name
            ?: customDrugName,
        onDrugSelected = { drugId ->
            scope.launch {
                schemeViewModel.saveDrugSelection(
                    drugId = drugId,
                    customName = ""
                )
            }
        },
        onNavigateBack = { navController.popBackStack() },
        onNavigateNext = {
            scope.launch {
                schemeViewModel.saveDrugSelection(
                    drugId = selectedDrugId,
                    customName = if (selectedDrugId == null) searchQuery else ""
                )
                navController.navigate(Graph.DATES_CHOICE)
            }
        }
    )
}