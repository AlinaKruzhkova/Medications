package com.example.myfirstapplication.drugchoice

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph

@Composable
fun DrugChoiceScreen(navController: NavController) {
    val itemsList = listOf(
        "Ампула(ы)", "Инъекция(и)", "Капля(и)", "Капсула(ы)", "Пакетик(и)", "Пшик(и)", "Таблетка(и)"
    )

    DrugChoiceContent(
        navigate = { navController.navigate(Graph.DATES_CHOICE) },
        navigateBack = { navController.popBackStack() },
        itemsList = itemsList
    )
}