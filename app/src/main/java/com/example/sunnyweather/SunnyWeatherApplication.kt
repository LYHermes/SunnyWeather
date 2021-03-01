package com.example.sunnyweather

import android.app.Application
import android.content.Context

/**
 * Created by Ethan on 2021/2/26
 *
 * @author Ethan
 */
class SunnyWeatherApplication : Application(){

    companion object{
        @SuppressWarnings("StaticFieldLeak")
        lateinit var context : Context
        const val TOKEN = "eoIkrkurmVakPFKH"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}