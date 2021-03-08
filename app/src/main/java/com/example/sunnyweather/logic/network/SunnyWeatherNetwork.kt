package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Ethan on 2021/2/26
 *
 * @author Ethan
 *
 * 网络数据源访问入口
 */
object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    //PlaceResponse
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    //天气
    suspend fun getDailyWeather(lng: String , lat: String) = weatherService.getDailyWeather(lng , lat).await()

    suspend fun getRealtimeWeather(lng: String , lat: String) = weatherService.getRealtimeWeather(lng , lat).await()

    //await()定义为Call<T>的扩展函数
    private suspend fun <T> Call<T>.await():T{

        return suspendCoroutine {
            continuation ->
            enqueue(object: Callback<T>{
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

            })
        }

    }
}