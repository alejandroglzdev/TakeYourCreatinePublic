package com.alejandroglzdev.takeyourcreatine.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alejandroglzdev.takeyourcreatine.R

val SourceCodePro = FontFamily(
    Font(R.font.source_code_pro_medium),
    Font(R.font.source_code_pro_italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = SourceCodePro,
        fontSize = 15.sp,
    ),

    headlineSmall = TextStyle(
        fontFamily = SourceCodePro,
        fontSize = 26.sp,
    )

)

val labelSmallAccent = Typography.labelSmall.copy(
    color = Accent
)

val labelSmallSecondaryDark = Typography.labelSmall.copy(
    color = SecondaryDark
)

val headlineSmallAccent = Typography.headlineSmall.copy(
    color = Accent
)
