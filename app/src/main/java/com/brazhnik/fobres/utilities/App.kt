package com.brazhnik.fobres.utilities

import android.app.Application
import android.content.Context
import android.content.res.Resources


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

    companion object {
        private var mContext: Context? = null
        val resources: Resources?
            get() = mContext?.resources
    }
}