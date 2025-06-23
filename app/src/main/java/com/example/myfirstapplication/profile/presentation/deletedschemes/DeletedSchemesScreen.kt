package com.example.myfirstapplication.profile.presentation.deletedschemes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.menu.presentation.DrugCardUi
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.menu.presentation.viewmodel.MenuViewModel
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DeletedSchemesScreen(navController: NavController) {

    val viewModel = hiltViewModel<MenuViewModel>()
    val drugViewModel = hiltViewModel<DrugViewModel>()
    val schemeViewModel = hiltViewModel<SchemeViewModel>()

    val drugs by drugViewModel.drugs.collectAsState()
    val schemesState by viewModel.deletedSchemesState.collectAsStateWithLifecycle()

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
                text = "Удаленные схемы:",
                fontFamily = customFont,
                fontSize = 24.sp,
                color = DeepBurgundy,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        if (schemesState.isEmpty()) {
            Text("Схем пока нет!", color = DarkBurgundy, fontSize = 16.sp)
        }

        //  Прокручиваемый список элементов
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(), // занимает всё пространство
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 8.dp,
            )
        ) {
            items(schemesState.size) { index ->
                val scheme = schemesState[index].second
                val drugMap = remember(drugs) { drugs.toMap() }
                val drugName = (scheme.customDrugName ?: drugMap[scheme.drugId]?.name).orEmpty()

                DrugCardUi(
                    drugName = drugName,
                    dosageInfo = if (scheme.endDate == null) {
                        "Бесконечно"
                    } else "Дата окончания: ${scheme.endDate}",
                    pillsLeft = scheme.numberOfPills,
                    pillsNotification = scheme.lowPillsNumber,
                    onDelete = { schemeViewModel.hardDeleteScheme((schemesState[index].first)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}