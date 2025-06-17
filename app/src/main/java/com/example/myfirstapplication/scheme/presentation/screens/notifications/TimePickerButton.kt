package com.example.myfirstapplication.scheme.presentation.screens.notifications


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose
import com.example.myfirstapplication.ui.theme.White
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime


@Composable
fun TimePickerButton(
    initialTime: LocalTime?,
    modifier: Modifier = Modifier,
    onTimeSelected: (LocalTime) -> Unit
) {
    var selectedTime by remember { mutableStateOf(initialTime ?: LocalTime.of(12, 0)) }
    val dialogState = rememberMaterialDialogState()

    Button(
        onClick = { dialogState.show() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = White
        )
    ) {
        Text(
            text = if (initialTime == null) {
                "Нажмите, чтобы выбрать время"
            } else "Вы выбрали: ${
                selectedTime.hour.toString().padStart(2, '0')
            }:${selectedTime.minute.toString().padStart(2, '0')}",
            fontFamily = customFont
        )
    }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = "ОК",
                textStyle = TextStyle(
                    color = Green,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    ) {
        timepicker(
            initialTime = selectedTime,
            is24HourClock = true,
            title = "Выбор времени",
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = Green,             // фон активной ячейки наверху!!!
                inactiveBackgroundColor = Pink,           // фон неактивной ячейки наверху и цвет круга!!!
                activeTextColor = DeepBurgundy,           // цвет текста в активной ячейке!!!
                inactiveTextColor = Rose,                 // цвет неактивного текста!!!
                selectorColor = Green,                  // цвет часового/минутного указателя!!!
                headerTextColor = DeepBurgundy,            // цвет заголовка!!!
                selectorTextColor = DeepBurgundy,           // цвет числа в минутном указателе!!!
            )
        ) { time ->
            selectedTime = time
            onTimeSelected(time)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TimePickerUiPreview() {
    TimePickerButton(
        initialTime = LocalTime.now(),
        onTimeSelected = {}
    )
}