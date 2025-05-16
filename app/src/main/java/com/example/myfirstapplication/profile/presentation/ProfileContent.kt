package com.example.myfirstapplication.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.myfirstapplication.profile.domain.UserProfile

@Composable
fun ProfileContent(
    user: UserProfile,
    logout: () -> Unit
) {
    Column {
        Text(text = user.name)
        Text(text = user.email)
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = "User avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Button(onClick = logout) {
            Text("Logout")
        }
    }
}
