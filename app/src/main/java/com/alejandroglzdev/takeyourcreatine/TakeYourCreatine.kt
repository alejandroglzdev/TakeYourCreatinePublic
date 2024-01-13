package com.alejandroglzdev.takeyourcreatine

import android.app.Application
import com.google.android.gms.ads.MobileAds

class TakeYourCreatine: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}