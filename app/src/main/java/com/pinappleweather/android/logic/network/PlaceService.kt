package com.pinappleweather.android.logic.network

import com.pinappleweather.android.SunnyWeatherApplication
import com.pinappleweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//定义API的Retrofit接口
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>
}