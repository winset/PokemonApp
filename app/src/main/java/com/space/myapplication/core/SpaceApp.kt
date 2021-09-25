package com.space.myapplication.core

import android.app.Application
import com.google.gson.Gson
import com.space.myapplication.data.PokemonCloudDataSource
import com.space.myapplication.data.PokemonsCloudMapper
import com.space.myapplication.data.PokemonRepository
import com.space.myapplication.data.cache.RealmProvider
import com.space.myapplication.data.cache.PokemonCacheDataSource
import com.space.myapplication.data.cache.PokemonsCacheMapper
import com.space.myapplication.data.ToPokemonMapper
import com.space.myapplication.data.cache.PokemonDataToDbMapper
import com.space.myapplication.data.net.UpcomingService
import com.space.myapplication.domain.*
import com.space.myapplication.presentation.BasePokemonDomainToUiMapper
import com.space.myapplication.presentation.MainViewModel
import com.space.myapplication.presentation.ResourceProvider
import com.space.myapplication.presentation.UpcomingCommunication
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpaceApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service = retrofit.create(UpcomingService::class.java)

        val upcomingCloudDataSource = PokemonCloudDataSource.Base(service, gson)
        val cacheDataSource =
            PokemonCacheDataSource.Base(RealmProvider.Base(), PokemonDataToDbMapper.Base())
        val toUpcomingMapper = ToPokemonMapper.Base()
        val upcomingListCloudMapper = PokemonsCloudMapper.Base(toUpcomingMapper)
        val upcomingListCacheMapper = PokemonsCacheMapper.Base(toUpcomingMapper)
        val upcomingRepository = PokemonRepository.Base(
            upcomingCloudDataSource,
            cacheDataSource,
            upcomingListCloudMapper,
            upcomingListCacheMapper
        )

        val upcomingsInteractor =
            PokemonsInteractor.Base(
                upcomingRepository, BasePokemonsDataToDomainMapper(
                    BasePokemonDataToDomainMapper()
                )
            )

        val communication = UpcomingCommunication.Base()
        mainViewModel = MainViewModel(
            upcomingsInteractor,
            PokemonsDomainToUiMapper.Base(ResourceProvider.Base(this), BasePokemonDomainToUiMapper()),
            communication
        )
    }
}