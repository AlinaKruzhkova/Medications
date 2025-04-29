package com.example.myfirstapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlaceholderScreen(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxSize(),
        style = MaterialTheme.typography.headlineLarge
    )
}
