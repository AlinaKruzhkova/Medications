package com.example.myfirstapplication.profile.presentation.druglist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.profile.domain.Drug
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun DrugCard(
    drug: Drug,
    showDescriptionAlways: Boolean = true,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
                onClick()
            },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GrayPink)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = drug.name,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = customFont
            )
            Text(
                text = drug.form,
                style = MaterialTheme.typography.labelLarge,
                color = Green,
                fontFamily = customFont
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (showDescriptionAlways) {
                DrugField(label = "Описание", content = drug.description)
            } else {
                AnimatedVisibility(
                    visible = expanded,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    DrugField(label = "Описание", content = drug.description)
                }
            }


            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column {
                    DrugField(label = "Показания", content = drug.indications)
                    DrugField(label = "Противопоказания", content = drug.contraindications)
                    DrugField(label = "Инструкция", content = drug.instruction)
                }
            }
        }
    }
}


@Composable
fun DrugField(label: String, content: String) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = Pink,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = DarkBurgundy,
            fontFamily = customFont
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = content,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}