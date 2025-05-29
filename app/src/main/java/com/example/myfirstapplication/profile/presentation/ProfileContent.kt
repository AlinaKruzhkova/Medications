package com.example.myfirstapplication.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myfirstapplication.R
import com.example.myfirstapplication.menu.customFont
import com.example.myfirstapplication.profile.domain.UserProfile
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.LightGreen
import com.example.myfirstapplication.ui.theme.Rose

@Composable
fun ProfileContent(
    user: UserProfile,
    logout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5DADA)) // розовый фон
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = "User avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Привет,",
                        color = LightGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont,
                    )
                    Text(
                        text = user.name,
                        color = DeepBurgundy,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont,
                    )
                }
            }

            IconButton(onClick = logout) {
                Icon(
                    imageVector = Icons.Default.ExitToApp, // можно заменить на кастомную иконку
                    contentDescription = "Logout",
                    tint = Color(0xFF8B004B)
                )
            }
        }
    }
}




@Preview (showBackground = true)
@Composable
fun ProfileContentPreview(){
    ProfileContent(
      user = UserProfile(
          name = "Alina",
          email = "akruzhochek@gmail.com",
          avatarUrl = "https://lh3.googleusercontent.com/a/ACg8ocKyWpvLQBbHFl99GmHTyoE_NbWRG_wmTxh-JNAq0tKQYksgmyUu=s432-c-no\n"
      )
    ) { }
}