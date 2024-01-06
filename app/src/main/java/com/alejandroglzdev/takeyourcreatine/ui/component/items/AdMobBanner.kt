package com.alejandroglzdev.takeyourcreatine.ui.component.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdmobBanner(padding: Dp = 0.dp, banner: String) {
    AndroidView(
        modifier = Modifier.fillMaxWidth().padding(padding),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = banner
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}