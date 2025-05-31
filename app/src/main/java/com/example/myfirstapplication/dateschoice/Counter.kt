package com.example.myfirstapplication.dateschoice

import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green


@Composable
fun CenteredNumberPicker(
    modifier: Modifier = Modifier,
    range: IntRange = 1..600,
    initialValue: Int = range.first,
    onNumberSelected: (Int) -> Unit
) {
    var selectedValue by remember { mutableStateOf(initialValue) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        NumberPicker(
            value = selectedValue,
            onValueChange = {
                selectedValue = it
                onNumberSelected(it)
            },
            range = range,
            textStyle = TextStyle(
                fontSize = 22.sp,
                color = DeepBurgundy
            ),
            dividersColor = Green,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CenteredNumberPickerPreview() {
    var selected by remember { mutableStateOf(45) }

    CenteredNumberPicker(
        onNumberSelected = { selected = it }
    )
}

