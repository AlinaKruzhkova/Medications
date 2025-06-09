package com.example.myfirstapplication.drug.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.navigation.Graph

@Composable
fun MenuScreen(navController: NavController) {
    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )
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