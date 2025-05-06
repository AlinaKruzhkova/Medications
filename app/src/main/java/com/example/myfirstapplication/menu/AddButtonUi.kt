package com.example.myfirstapplication.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.Burgundy
import com.example.myfirstapplication.ui.theme.Pink

@Composable
fun AddButtonUi(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(80.dp)
            .background(
                color = Burgundy,
                shape = RoundedCornerShape(32.dp)
            )
            .clickable { },

        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.add),
            contentDescription = null
        )
        }
    }



@Preview(showBackground = true)
@Composable
fun AddButtonPreview() {
    AddButtonUi(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { }
}