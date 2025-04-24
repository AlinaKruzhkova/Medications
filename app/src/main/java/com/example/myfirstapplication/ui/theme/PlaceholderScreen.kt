package com.example.myfirstapplication.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlaceholderScreen(title: String) {
    androidx.compose.material3.Text(
        text = title,
        modifier = Modifier.fillMaxSize(),
        style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
    )
}
