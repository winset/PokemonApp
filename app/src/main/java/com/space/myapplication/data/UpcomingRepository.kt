package com.space.myapplication.data

import java.lang.Exception

interface UpcomingRepository {
    suspend fun getUpcoming(): UpcomingData

    class Base(private val cacheDataSource: CacheDataSource) : UpcomingRepository {
        override suspend fun getUpcoming() = try {
            UpcomingData.Success(cacheDataSource.getUpcoming())
        } catch (exception: Exception) {
            UpcomingData.Fail(exception)
        }
    }
}