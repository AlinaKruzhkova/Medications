package com.example.myfirstapplication.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink

@Composable//аннотация, кот показывает что функция ниже будет отображать
fun AuthorizationButtonUi(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(280.dp)
            .background(
                color = GrayPink,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {  },

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.authorization_text),
            color = DarkBurgundy,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AuthorizationButtonPreview() {
    AuthorizationButtonUi(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { }
}
