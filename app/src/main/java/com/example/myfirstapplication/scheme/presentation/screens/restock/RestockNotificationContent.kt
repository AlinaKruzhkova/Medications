package com.example.myfirstapplication.scheme.presentation.screens.restock

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.common.ui.BackButton
import com.example.myfirstapplication.drug.presentation.customFont
import com.example.myfirstapplication.scheme.presentation.screens.frequency.FrequencySelectedOption
import com.example.myfirstapplication.ui.theme.DarkGreen
import com.example.myfirstapplication.ui.theme.DeepBurgundy
import com.example.myfirstapplication.ui.theme.Green
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun RestockNotificationContent(
    navigate: () -> Unit,
    onDataSelected: (Pair<Int, Int>?) -> Unit,
    navigateBack: () -> Unit,
) {

    var numberOfPills: Int? by remember { mutableStateOf(null) }
    var lowNumberOfPills: Int? by remember { mutableStateOf(null) }
    var isChecked by remember { mutableStateOf(false) }

    val allFieldsValid = numberOfPills != null && lowNumberOfPills != null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink)
    ) {
        BackButton(
            onClick = navigateBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // отступ под кнопку
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Spacer(modifier = Modifier.height(36.dp))

            Image(
                painter = painterResource(R.drawable.blister),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.restock_text),
                color = DeepBurgundy,
                fontSize = 16.sp,
                fontFamily = customFont
            )

            Spacer(modifier = Modifier.height(32.dp))

            ReminderButton(
                isChecked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    numberOfPills = null
                    lowNumberOfPills = null
                }
            )

            AnimatedVisibility(
                visible = isChecked,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)) + expandVertically(),
                exit = fadeOut(animationSpec = tween(durationMillis = 500)) + shrinkVertically()
            ){
                Spacer(modifier = Modifier.height(32.dp))

                RestockFields { number, low ->
                    numberOfPills = number
                    lowNumberOfPills = low
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }



        // Кнопка внизу экрана
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Pink)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val scope = rememberCoroutineScope()

            val TAG = "SAVEBUTTON"
            SaveButtonUi(
                onClick = {
                    Log.d(
                        TAG,
                        "Save clicked - values: $numberOfPills, $lowNumberOfPills, $isChecked",
                    )
                    if (allFieldsValid) {
                        onDataSelected(Pair(numberOfPills!!, lowNumberOfPills!!))
                        navigate()
                    } else {
                        Log.d(TAG, "Validation failed")
                    }
                },
                isActive = allFieldsValid
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RestockNotificationContentPreview() {
    RestockNotificationContent(
        navigate = {},
        onDataSelected = {},
        navigateBack = {}
    )
}



@Composable
fun ReminderButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val borderColor = if (isChecked) DarkGreen else Color.DarkGray

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
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.nunito_extralight)),
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(50))
                .background(if (isChecked) Green else Color.Gray)
                .border(1.dp, borderColor, RoundedCornerShape(50))
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
                        tint = Green,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ReminderButtonPreview() {
    var isChecked by remember { mutableStateOf(false) }

    ReminderButton(
        isChecked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}
