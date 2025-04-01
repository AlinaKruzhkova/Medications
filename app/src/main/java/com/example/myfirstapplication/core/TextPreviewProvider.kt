package com.example.myfirstapplication.core


import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TextPreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Short Text",
        "A bit longer text. A bit longer text. A bit longer text. A bit longer text.",
        "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!"
    )
}

@Preview(
    name = "Default",
    group = "Default",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    name = "Night theme",
    group = "Night theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    name = "Small screen",
    group = "Small screen",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 300,
    heightDp = 500
)
annotation class ScreenPreview