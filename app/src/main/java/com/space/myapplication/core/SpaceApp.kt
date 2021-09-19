package com.space.myapplication.core

import android.app.Application
import com.space.myapplication.data.UpcomingCloudDataSource
import com.space.myapplication.data.UpcomingDataToDomainMapper
import com.space.myapplication.data.UpcomingListCloudMapper
import com.space.myapplication.data.UpcomingRepository
import com.space.myapplication.data.cache.RealmProvider
import com.space.myapplication.data.cache.UpcomingCacheDataSource
import com.space.myapplication.data.cache.UpcomingCacheMapper
import com.space.myapplication.data.cache.UpcomingListCacheMapper
import com.space.myapplication.data.net.UpcomingDtoMapper
import com.space.myapplication.data.net.UpcomingService
import com.space.myapplication.domain.UpcomingsDomainToUiMapper
import com.space.myapplication.domain.UpcomingsInteractor
import com.space.myapplication.presentation.MainViewModel
import com.space.myapplication.presentation.ResourceProvider
import com.space.myapplication.presentation.UpcomingCommunication
import io.realm.Realm
import retrofit2.Retrofit

class SpaceApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
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

        val upcomingsInteractor =
            UpcomingsInteractor.Base(upcomingRepository, UpcomingDataToDomainMapper.Base())

        val communication = UpcomingCommunication.Base()
        mainViewModel = MainViewModel(
            upcomingsInteractor,
            UpcomingsDomainToUiMapper.Base(
                communication,
                ResourceProvider.Base(this)
            ),
            communication
        )
    }
}