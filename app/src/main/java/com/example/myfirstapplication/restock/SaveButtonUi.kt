package com.example.myfirstapplication.restock

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.Burgundy
import com.example.myfirstapplication.ui.theme.Grey
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun SaveButtonUi(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isActive: Boolean = true
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Burgundy else Grey,
        animationSpec = tween(durationMillis = 500),
        label = "buttonBackgroundColor"
    )

    Box(
        modifier = Modifier
            .height(50.dp)
            .width(280.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(enabled = isActive) { onClick },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.save_text),
            color = Pink,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SaveButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SaveButtonUi(
            isActive = true,
            onClick = {}
        )
        SaveButtonUi(
            isActive = false,
            onClick = {}
        )
    }
}
