package com.space.myapplication.data

import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingService

interface UpcomingCloudDataSource {
    suspend fun getUpcoming(): List<UpcomingDto>

    class Base(private val service: UpcomingService) : UpcomingCloudDataSource {
        override suspend fun getUpcoming(): List<UpcomingDto> {
            return service.getUpcoming()
        }
    }
}