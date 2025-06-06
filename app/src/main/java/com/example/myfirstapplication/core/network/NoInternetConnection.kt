package com.example.myfirstapplication.core.network

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.drug.presentation.customFont

@Composable
fun NoInternet() {
    val backgroundColor = MaterialTheme.colorScheme.errorContainer
    val textColor = MaterialTheme.colorScheme.onErrorContainer

    Box(
        Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.no_internet),
            color = textColor,
            style = TextStyle(
                fontFamily = customFont
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetPreview() {
    MaterialTheme {
        NoInternet()
    }
}