package com.example.myfirstapplication.scheme.presentation.drugchoice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch

@Composable
fun DrugChoiceScreen(navController: NavController) {
    // viewModel
    val drugViewModel = hiltViewModel<DrugViewModel>()
    val schemeViewModel = hiltViewModel<SchemeViewModel>()
    val scope = rememberCoroutineScope()

    val drugs by drugViewModel.drugs.collectAsState()
    val searchQuery by drugViewModel.searchQuery.collectAsState()
    val currentScheme by schemeViewModel.currentScheme.collectAsState()

    DrugChoiceContent(
        searchQuery = searchQuery,
        onSearchQueryChanged = { query ->
            drugViewModel.onSearchQueryChanged(query)
            // Если пользователь начал вводить текст, сбрасываем выбранный препарат
            if (query.isNotEmpty() && currentScheme.drugId != null) {
                scope.launch { // Добавляем вызов в корутину
                    schemeViewModel.saveDrugSelection(
                        drugId = null,
                        customName = query
                    )
                }
            }
        },
        drugs = drugs,
        selectedDrugId = currentScheme.drugId,
        selectedDrugName = drugs.firstOrNull { it.first == currentScheme.drugId }?.second?.name
            ?: currentScheme.customDrugName,
        onDrugSelected = { drugId ->
            scope.launch {
                schemeViewModel.saveDrugSelection(
                    drugId = drugId,
                    customName = null
                )
            }
        },
        onNavigateBack = { navController.popBackStack() },
        onNavigateNext = {
            scope.launch {
                schemeViewModel.saveDrugSelection(
                    drugId = currentScheme.drugId,
                    customName = if (currentScheme.drugId == null) searchQuery else null
                )
                navController.navigate(Graph.DATES_CHOICE)
            }
        },
        schemeId = schemeViewModel.schemeId
    )
}