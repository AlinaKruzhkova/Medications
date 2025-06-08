package com.example.myfirstapplication.restock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun RestockNotificationScreenUi (navigate: () -> Unit) {
    val customFont = FontFamily(
        Font(R.font.rubik_one_regular)
    )
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем содержимое по горизонтали
        verticalArrangement = Arrangement.Top // Центрируем содержимое по вертикали
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Image(
            painterResource(R.drawable.blister),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.restock_text),
            color = DeepBurgundy,
            fontSize = 16.sp,
            fontFamily = customFont,
        )
        Spacer(modifier = Modifier.height(32.dp))

        ReminderToggle(
            isChecked = isChecked,
            onCheckedChange = { isChecked = it }
        )

    }
}



@Preview(showBackground = true)
@Composable
fun RestockNotificationScreenPreview() {
    RestockNotificationScreenUi (navigate = {})
}


@Composable
fun ReminderToggle(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = "Получать напоминания",
            color = DeepBurgundy,
            fontSize = 16.sp
        )

        //Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(50))
                .background(if (isChecked) Color(0xFF4CAF50) else Color.Gray)
                .clickable { onCheckedChange(!isChecked) },
            contentAlignment = if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ReminderTogglePreview() {
    var isChecked by remember { mutableStateOf(false) }

    ReminderToggle(
        isChecked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}
