package com.pinappleweather.android.logic.dao


import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.pinappleweather.android.SunnyWeatherApplication
import com.pinappleweather.android.logic.model.Place

// 将曾经查询过的地址记录下来，封装一系列的方法
object PlaceDao {
    //写入地点
    fun savePlace(place:Place){
        sharedPreferences().edit{
            putString("place",Gson().toJson(place))
        }
    }

    //读取地点
    fun getSavedPlace():Place{
        val placeJson=sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    //判断是否有数据被存储
    fun isPlaceSaved()=sharedPreferences().contains("place")

    private fun sharedPreferences()=SunnyWeatherApplication.context.getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}