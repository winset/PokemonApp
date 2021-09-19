package com.space.myapplication.data

import com.space.myapplication.data.cache.UpcomingCacheDataSource
import com.space.myapplication.data.cache.UpcomingListCacheMapper
import kotlinx.coroutines.delay
import java.lang.Exception

interface UpcomingRepository {
    suspend fun getUpcoming(): UpcomingListData

    class Base(
        private val upcomingCloudDataSource: UpcomingCloudDataSource,
        private val cacheDataSource: UpcomingCacheDataSource,
        private val upcomingListCloudMapper: UpcomingListCloudMapper,
        private val upcomingListCacheMapper: UpcomingListCacheMapper
    ) : UpcomingRepository {
        override suspend fun getUpcoming() = try {
            delay(1000)//todo just for test
            val upcomingCacheList = cacheDataSource.getUpcomingList()
            if (upcomingCacheList.isEmpty()) {
                val upcomingCloudList = upcomingCloudDataSource.getUpcoming()
                val upcomingList = upcomingListCloudMapper.map(upcomingCloudList)
                cacheDataSource.saveUpcomingList(upcomingList)
                UpcomingListData.Success(upcomingList)
            } else {
                UpcomingListData.Success(upcomingListCacheMapper.map(upcomingCacheList))
            }
        } catch (exception: Exception) {
            UpcomingListData.Fail(exception)
        }
    }
}