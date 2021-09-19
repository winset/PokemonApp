package com.space.myapplication.data

import android.animation.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingService

interface UpcomingCloudDataSource {
    suspend fun getUpcoming(): List<UpcomingDto>

    class Base(private val service: UpcomingService) : UpcomingCloudDataSource {
        private val gson = Gson()
        private val type = object :TypeToken<List<UpcomingDto>>(){}.type
        override suspend fun getUpcoming(): List<UpcomingDto> {
            return gson.fromJson<List<UpcomingDto>>(service.getUpcoming().string(),type)
        }
    }
}