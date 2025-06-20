package com.example.myfirstapplication.scheme.presentation.screens.dateschoice.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green


@Composable
fun Counter(
    modifier: Modifier = Modifier,
    range: IntRange = 1..600,
    initialValue: Int? = null,
    onNumberSelected: (Int) -> Unit
) {

    var selectedValue by remember { mutableIntStateOf(initialValue ?: range.first) }

    LaunchedEffect(initialValue) {
        if (initialValue != null) {
            selectedValue = initialValue
        }
    }

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
    var selected by remember { mutableIntStateOf(45) }

    Counter(
        onNumberSelected = { selected = it }
    )
}

