package com.space.myapplication.core

import android.app.Application
import com.space.myapplication.data.net.UpcomingService
import retrofit2.Retrofit

class SpaceApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .build()
        val service = retrofit.create(UpcomingService::class.java)

    }
}