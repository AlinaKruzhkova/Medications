package com.example.myfirstapplication.scheme.presentation.screens.restock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import kotlinx.coroutines.launch

@Composable
fun RestockNotificationScreen(navController: NavController) {

    val schemeViewModel = hiltViewModel<SchemeViewModel>()
    val scope = rememberCoroutineScope()

    RestockNotificationContent(
        navigate = { navController.navigate(Graph.HOME) },
        onDataSelected = { data ->
            scope.launch {
                schemeViewModel.savePillInfo(data)
                schemeViewModel.finalize()
            }
        }
    )
}