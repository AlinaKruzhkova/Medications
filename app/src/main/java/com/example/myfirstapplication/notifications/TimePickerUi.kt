package com.example.myfirstapplication.notifications


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose
import com.example.myfirstapplication.ui.theme.White
import com.vanpra.composematerialdialogs.*
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime


val customFont = FontFamily(
    Font(R.font.rubik_one_regular)
)

@Composable
fun TimePickerUi(
    modifier: Modifier = Modifier,
    onTimeSelected: (LocalTime) -> Unit = {}
) {
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    val dialogState = rememberMaterialDialogState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Pink, shape = RoundedCornerShape(16.dp))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { dialogState.show() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White
            )
        ) {
            Text(text = "Выберите время")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedTime?.let {
            Text(
                text = "Вы выбрали: ${it.hour.toString().padStart(2, '0')}:${it.minute.toString().padStart(2, '0')}",
                fontSize = 16.sp,
                fontFamily = customFont,
                color = DeepBurgundy
            )
        }
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
            //negativeButton(text = "Отмена")
        }
    ) {
        timepicker(
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
    TimePickerUi()
}