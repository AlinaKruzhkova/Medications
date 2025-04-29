package com.example.myfirstapplication.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.R

@Composable
fun NoInternet() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.purple_200)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.no_internet),
            color = colorResource(R.color.black),
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