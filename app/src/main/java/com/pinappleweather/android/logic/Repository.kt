package com.pinappleweather.android.logic

import androidx.lifecycle.liveData
import com.pinappleweather.android.logic.dao.PlaceDao
import com.pinappleweather.android.logic.model.Place
import com.pinappleweather.android.logic.model.RealtimeResponse
import com.pinappleweather.android.logic.model.Weather
import com.pinappleweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

//仓库层的统一封装入口
object Repository {
    fun searchPlaces(query:String)= fire(Dispatchers.IO) {
            val placeResponse=SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status== "ok"){
                val places=placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
    }

    //获取实时天气的方法
    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO) {
            coroutineScope {
                val deferredRealtime=async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng,lat)
                }
                val deferredDaily=async {
                    SunnyWeatherNetwork.getDailyWeather(lng,lat)
                }
                val realtimeResponse=deferredRealtime.await()
                val dailyResponse=deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status=="ok"){
                    val weather= Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                            RuntimeException(
                                    "realtime response status is ${realtimeResponse.status}"+
                                            "daily response status is ${dailyResponse.status}"
                            )
                    )
                }
            }
        }


    //修改：封装成一个函数，这样减少try catch块的使用
    private fun <T> fire(context: CoroutineContext,block:suspend ()->Result<T>)=
            liveData<Result<T>>(context) {
                val result=try {
                    block()
                } catch (e:Exception){
                    Result.failure<T>(e)
                }
                emit(result)
            }

    //保存存储地点的方法
    fun savePlace(place:Place)=PlaceDao.savePlace(place)

    fun getSavedPlace()=PlaceDao.getSavedPlace()

    fun isPlaceSaved()=PlaceDao.isPlaceSaved()
}