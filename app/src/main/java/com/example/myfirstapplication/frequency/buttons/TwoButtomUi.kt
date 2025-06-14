package com.example.myfirstapplication.frequency.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.White

@Composable
fun TwoButtonUi(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val customFont = FontFamily(Font(R.font.nunito_extralight))

    val backgroundColor = if (isSelected) Green else White
    val textColor = if (isSelected) White else DeepBurgundy
    val icon = if (isSelected) R.drawable.selected else R.drawable.not_selected

    Box(
        modifier = Modifier
            .height(58.dp)
            .width(304.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(32.dp)
            )
            .clickable(enabled = !isSelected) { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize() // Занимает всю высоту и ширину Box
                .padding(start = 16.dp), // Отступ слева
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.two_button),
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TwoButtonPreview() {
    TwoButtonUi(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        isSelected = false,
        onClick = {}
    )
}
