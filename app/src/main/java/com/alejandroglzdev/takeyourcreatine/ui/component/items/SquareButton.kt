package com.alejandroglzdev.takeyourcreatine.ui.component.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.ui.theme.Accent
import com.alejandroglzdev.takeyourcreatine.ui.theme.SecondaryDark

@Composable
fun SquareButton(
    onClick: () -> Unit,
    content: String
) {
    val squareShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline = Outline.Rectangle(
            androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height)
        )
    }

    Button(
        onClick = { onClick },
        content = { Text(text = content, style = MaterialTheme.typography.labelSmall) },
        shape = squareShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryDark,
            contentColor = Accent,
            disabledContainerColor = SecondaryDark,
            disabledContentColor = Accent
        ),
        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp)

        )
}