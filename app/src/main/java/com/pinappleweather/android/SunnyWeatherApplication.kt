package com.pinappleweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application() {
    // 全局获取Context,方便调用
    companion object{
        const val TOKEN="Iy6cInx6wknJY4fA" //API令牌
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}