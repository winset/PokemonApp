package com.space.myapplication.data

import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingService

interface CacheDataSource {
    suspend fun getUpcoming(): List<UpcomingDto>

    class Base(private val service: UpcomingService) : CacheDataSource {
        override suspend fun getUpcoming(): List<UpcomingDto> {
            return service.getUpcoming()
        }
    }
}