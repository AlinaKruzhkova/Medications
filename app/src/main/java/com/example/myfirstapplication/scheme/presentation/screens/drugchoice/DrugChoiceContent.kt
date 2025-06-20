package com.example.myfirstapplication.scheme.presentation.screens.drugchoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.profile.domain.Drug
import com.example.myfirstapplication.profile.presentation.druglist.DrugCard
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DrugChoiceContent(
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit,
    drugs: List<Pair<String, Drug>>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    selectedDrugId: String?,
    onDrugSelected: (String) -> Unit,
    selectedDrugName: String?,
    schemeId: String?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 72.dp), // место под кнопку + отступ
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(onClick = navigateBack)
            .padding(16.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок 1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = onNavigateBack
            )

                Text(
                    text = stringResource(R.string.drug_choice),
                    style = TextStyle(
                        fontFamily = customFont,
                        fontSize = 18.sp,
                        color = DeepBurgundy,
                        lineHeight = 24.sp
                    ),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

        // Поле ввода
        SearchDrugField(
            query = searchQuery,
            onQueryChanged = onSearchQueryChanged
        )

            Spacer(modifier = Modifier.height(12.dp))

            // Список лекарств
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // список тянется по доступной высоте
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(drugs.size) { index ->
                    DrugCard(
                        drug = drugs[index].second,
                        showDescriptionAlways = false,
                        onClick = { onDrugSelected(drugs[index].second.name) }
                    )
                }
            }

        Text(
            text = when {
                selectedDrugId.isNullOrEmpty() && searchQuery.isNullOrEmpty() -> "Вы ничего не выбрали"
                selectedDrugId.isNullOrEmpty() -> "Вы выбрали: $searchQuery"
                else -> "Вы выбрали: $selectedDrugName"
            },
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = DeepBurgundy
        )

        // Кнопка далее внизу экрана
        NextButton(
            onClick = onNavigateNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isActive = selectedDrugId?.isNotEmpty() == true || searchQuery.isNotEmpty()
        )
    }
}


//красивое поле ввода для списка лекарств
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDrugField(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        shape = RoundedCornerShape(10.dp),
        maxLines = 1,
        label = {
            Text(stringResource(R.string.search_drug_field))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear, null
                    )
                }
            }
        }, colors = TextFieldDefaults.outlinedTextFieldColors(
            // Цвет фона до и после нажатия
            containerColor = Color(0xFFFFFFFE), // для фонового цвета
            focusedBorderColor = Color(0x8075AD7A), // граница при фокусе
            unfocusedBorderColor = Color(0x8075AD7A), // граница без фокуса
            cursorColor = Color(0x8075AD7A), // цвет курсора
            focusedLabelColor = Color(0xFF924959), // цвет текста в label при фокусе
            unfocusedLabelColor = Color(0xFF371628) // цвет текста в label без фокуса
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchUser() {
    SearchDrugField("") {}
}