package com.example.myfirstapplication.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.ui.theme.Burgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable//аннотация, кот показывает что функция ниже будет отображать
fun AddButtonUi(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(280.dp)
            .background(
                color = Burgundy,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { },

        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Next",
                tint = Pink
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Добавить новый препарат",
                color = Pink,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddButtonPreview() {
    AddButtonUi(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { }
}