package com.example.myfirstapplication.drugchoice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.drug.presentation.MenuContent
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DrugChoiceContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    itemsList: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
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
                onClick = navigateBack
            )

            Text(
                text = stringResource(R.string.choice),
                style = TextStyle(
                    fontFamily = customFont,
                    fontSize = 18.sp,
                    color = DeepBurgundy,
                    lineHeight = 24.sp
                ),
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // Прокручиваемый список возможных форм выпуска
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 3000.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
            ) {
                items(itemsList) { itemText ->
                    DrugsFormChoose(itemText, {})
                    Spacer(modifier = Modifier.height(12.dp)) // отступ между карточками
                }
            }

            // Верхний градиент
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Pink, Pink.copy(alpha = 0f))
                        )
                    )
            )

            // Нижний градиент
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Pink.copy(alpha = 0f), Pink)
                        )
                    )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Заголовок 2
        Text(
            text = stringResource(R.string.drug_choice), style = TextStyle(
                fontFamily = customFont, fontSize = 18.sp, color = DeepBurgundy
            ), modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Поле ввода
        SearchDrugField(
            onQueryChanged = {},
            query = ""
        )

        Spacer(modifier = Modifier.height(54.dp))

        // Кнопка далее
        NextButton(
            onClick = navigate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun DrugsFormChoose(
    drugFormat: String, onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(40.dp)
            .background(
                color = Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick },

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = drugFormat, color = DeepBurgundy, fontSize = 16.sp, fontWeight = FontWeight.Bold
        )
    }
}


//красивое поле ввода для списка лекарств
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDrugField(
    query: String, onQueryChanged: (String) -> Unit
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

@Preview(showBackground = true)
@Composable
fun PreviewMenuContent() {
    MenuContent(
        navigate = { },
        itemsList = listOf(
            "Ампула(ы)",
            "Инъекция(и)",
            "Капля(и)",
            "Капсула(ы)",
            "Пакетик(и)",
            "Пшик(и)",
            "Таблетка(и)"
        )
    )
}