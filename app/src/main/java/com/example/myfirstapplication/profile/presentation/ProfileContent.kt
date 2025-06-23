package com.example.myfirstapplication.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myfirstapplication.menu.presentation.customFont
import com.example.myfirstapplication.profile.domain.UserProfile
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.LightGreen
import com.example.myfirstapplication.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    user: UserProfile,
    logout: () -> Unit,
    navigateToDrugListScreen: () -> Unit,
    navigateToDeletedSchemesScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = "User avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Привет,",
                        color = LightGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont,
                    )
                    Text(
                        text = user.name,
                        color = DeepBurgundy,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont,
                    )
                }
            }

            IconButton(onClick = logout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Logout",
                    tint = DarkBurgundy
                )
            }
        }

        ProfileActionButton(
            navigate = navigateToDrugListScreen,
            text = "Найти препараты",
            imageVector = Icons.Default.Search
        )

        ProfileActionButton(
            navigate = navigateToDeletedSchemesScreen,
            text = "Удаленные схемы",
            imageVector = Icons.Default.DateRange
        )

        ProfileActionButton(
            navigate = {},
            text = "Избранные лекарства",
            imageVector = Icons.Default.Favorite
        )

        ProfileActionButton(
            navigate = {},
            text = "Поддержка",
            imageVector = Icons.Default.Phone
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    ProfileContent(
        user = UserProfile(
            name = "Alina",
            email = "akruzhochek@gmail.com",
            avatarUrl = "https://lh3.googleusercontent.com/a/ACg8ocKyWpvLQBbHFl99GmHTyoE_NbWRG_wmTxh-JNAq0tKQYksgmyUu=s432-c-no\n"
        ),
        logout = {},
        navigateToDrugListScreen = {},
        navigateToDeletedSchemesScreen = {},
    )
}