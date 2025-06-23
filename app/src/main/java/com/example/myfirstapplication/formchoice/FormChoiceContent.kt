package com.example.myfirstapplication.formchoice

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DrugsFormChoose(
    drugFormat: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) Green.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.5f)
    val textColor = if (isSelected) Color.White else DeepBurgundy

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(40.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick() },

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = drugFormat,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun FormChoiceContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    forms: List<String>,
    query: String,
    onQueryChanged: (String) -> Unit,
    selectedForm: String?,
    onFormSelected: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onClick = navigateBack)

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

        Spacer(modifier = Modifier.height(12.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(forms.size) { index ->
                    val form = forms[index]
                    DrugsFormChoose(
                        drugFormat = form,
                        isSelected = form == selectedForm,
                        onClick = { onFormSelected(form) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // Верхний градиент
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Pink, Pink.copy(alpha = 0f))
                        )
                    )
            )

            // Нижний градиент
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Pink.copy(alpha = 0f), Pink)
                        )
                    )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        NextButton(
            onClick = navigate,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isActive = !selectedForm.isNullOrEmpty()

        )
    }
}


@Preview(showBackground = true)
@Composable
fun FormChoiceContentPreview() {
    MaterialTheme {
        val fakeForms = listOf("Таблетки", "Капсулы", "Сироп", "Капли")

        FormChoiceContent(
            navigate = {},
            navigateBack = {},
            forms = fakeForms,
            query = "fdsdfs",
            onQueryChanged = {},
            selectedForm = "asda",
            onFormSelected = {}
        )
    }
}