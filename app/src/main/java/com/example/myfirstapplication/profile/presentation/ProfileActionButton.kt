package com.example.myfirstapplication.profile.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink

@Composable
fun ProfileActionButton(
    navigate: () -> Unit,
    text: String,
    imageVector: ImageVector
) {
    Button(
        onClick = navigate,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GrayPink,
            contentColor = DeepBurgundy
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Search Drugs",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = customFont
        )
    }
}