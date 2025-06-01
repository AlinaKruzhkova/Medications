package com.example.myfirstapplication.drug

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose

@Composable
fun MenuScreenUi (navigate: () -> Unit) {
    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )
    val itemsList = List(10) {
        "Элемент ${it + 1}"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(12.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
    ) {
        //Фикс заголовок с иконкой
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Центрируем иконку и текст в строке
        ) {
            Image(
                painterResource(R.drawable.capsule_pill),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Text(
                text = stringResource(R.string.title_text),
                color = Rose,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
            )
        }

// Контейнер для списка с градиентом
Box(
    modifier = Modifier
        .weight(1f) // Занимает оставшееся пространство
        .fillMaxWidth()
) {
    //  Прокручиваемый список элементов
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth(), // занимает всё пространство
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp,
        )
    ) {
        items(itemsList) { itemText ->
            DrugCardUi(
                drugName = itemText,
                dosageInfo = "Ежедневно",
                pillsLeft = 5,
                {}
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
// Градиент в верхней части списка
    Box(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .height(40.dp) // Высота градиента
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Pink, Pink.copy(alpha = 0f)) // От цвета фона до прозрачного
                )
            )
    )
    // Градиент снизу
    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(40.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Pink.copy(alpha = 0f), Pink)
                )
            )
    )
}
        // Обертка для кнопки, чтобы разместить ее справа
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            AddButtonUi(
                onClick = navigate,
                modifier = Modifier
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreenUi(navigate = {})
}




// TOD: красивый lazycolumn и переместить кнопку вправо и попробовать сделать тень как в фигма

