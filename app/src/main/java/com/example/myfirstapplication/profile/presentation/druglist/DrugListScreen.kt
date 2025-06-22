package com.example.myfirstapplication.profile.presentation.druglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel
import com.example.myfirstapplication.scheme.presentation.screens.drugchoice.SearchDrugField
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DrugListScreen(navController: NavController) {
    val viewModel = hiltViewModel<DrugViewModel>()
    val drugs by viewModel.drugs.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 32.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton { navController.popBackStack() }

            Text(
                text = "Найдите лекарство:",
                fontFamily = customFont,
                fontSize = 24.sp,
                color = DeepBurgundy,
                modifier = Modifier.padding(start = 8.dp)
            )
        }


        SearchDrugField(
            query = query,
            onQueryChanged = viewModel::onSearchQueryChanged
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(drugs.size) { index ->
                DrugCard(
                    drug = drugs[index].second,
                    onClick = {}
                )
            }
        }
    }
}
