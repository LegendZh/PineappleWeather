package com.pinappleweather.android.logic.network

import com.pinappleweather.android.SunnyWeatherApplication
import com.pinappleweather.android.logic.model.DailyResponse
import com.pinappleweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//调用天气相关API的接口
interface WeatherService {
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json") //版本号根据我们的API版本号进行更改
    fun getRealtimeWeather(@Path("lng") lng:String,@Path("lat") lat:String):
            Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng:String,@Path("lat") lat:String):
            Call<DailyResponse>
}