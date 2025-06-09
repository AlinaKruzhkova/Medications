package com.example.myfirstapplication.drug.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myfirstapplication.navigation.Graph

@Composable
fun MenuScreen(navController: NavController) {
    val itemsList = List(10) {
        "Элемент ${it + 1}"
    }

    MenuContent(
        navigate = {
            navController.navigate(Graph.FORM_CHOICE)
        },
        itemsList = itemsList
    )
}