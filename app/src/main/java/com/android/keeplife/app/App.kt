package com.android.keeplife.app

import android.app.Application
import com.android.apphelper2.app.AppHelperManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppHelperManager.init(this)
    }

}