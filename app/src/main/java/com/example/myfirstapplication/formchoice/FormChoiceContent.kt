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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.common.ui.NextButton
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun FormChoiceContent(
    navigate: () -> Unit,
    navigateBack: () -> Unit,
    forms: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(16.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
            ) {
                items(forms.size) { index ->
                    DrugsFormChoose(forms[index], {})
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
    drugFormat: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(40.dp)
            .background(
                color = Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick },

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = drugFormat, color = DeepBurgundy, fontSize = 16.sp, fontWeight = FontWeight.Bold
        )
    }
}