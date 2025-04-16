package com.example.myfirstapplication.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.Rose


@Composable
fun LoginScreenUi(navigate: () -> Unit) {

    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(12.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем содержимое по вертикали
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 36.dp)
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
        Text(
            text = stringResource(R.string.hello_text),
            color = DeepBurgundy,
            fontSize = 15.sp, // Размер шрифта для подзаголовка
            modifier = Modifier.padding(bottom = 24.dp), // Отступ снизу (опционально)
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            lineHeight = 24.sp
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        LoginButtonUi(
            onClick = navigate,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenUi(navigate = {})
}
