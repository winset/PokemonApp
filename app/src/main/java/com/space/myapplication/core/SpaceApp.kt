package com.space.myapplication.core

import android.app.Application
import com.space.myapplication.data.UpcomingCloudDataSource
import com.space.myapplication.data.UpcomingListCloudMapper
import com.space.myapplication.data.UpcomingRepository
import com.space.myapplication.data.cache.RealmProvider
import com.space.myapplication.data.cache.UpcomingCacheDataSource
import com.space.myapplication.data.cache.UpcomingCacheMapper
import com.space.myapplication.data.cache.UpcomingListCacheMapper
import com.space.myapplication.data.net.UpcomingDtoMapper
import com.space.myapplication.data.net.UpcomingService
import retrofit2.Retrofit

class SpaceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .build()
        val service = retrofit.create(UpcomingService::class.java)

        val upcomingCloudDataSource = UpcomingCloudDataSource.Base(service)
        val cacheDataSource = UpcomingCacheDataSource.Base(RealmProvider.Base())
        val upcomingListCloudMapper = UpcomingListCloudMapper.Base(UpcomingDtoMapper.Base())
        val upcomingListCacheMapper = UpcomingListCacheMapper.Base(UpcomingCacheMapper.Base())
        val upcomingRepository = UpcomingRepository.Base(
            upcomingCloudDataSource,
            cacheDataSource,
            upcomingListCloudMapper,
            upcomingListCacheMapper
        )
    }
}