package com.space.myapplication.core

import android.app.Application
import com.google.gson.Gson
import com.space.myapplication.data.pokemons.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.PokemonsCloudMapper
import com.space.myapplication.data.pokemons.PokemonRepository
import com.space.myapplication.data.pokemons.cache.RealmProvider
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.ToPokemonMapper
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.net.PokemonService
import com.space.myapplication.domain.*
import com.space.myapplication.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApp : Application() {

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
        val service = retrofit.create(PokemonService::class.java)

        val pokemonCloudDataSource = PokemonCloudDataSource.Base(service)
        val cacheDataSource =
            PokemonCacheDataSource.Base(RealmProvider.Base(), PokemonDataToDbMapper.Base())
        val toPokemonMapper = ToPokemonMapper.Base()
        val pokemonsCloudMapper = PokemonsCloudMapper.Base(toPokemonMapper)
        val pokemonsCacheMapper = PokemonsCacheMapper.Base(toPokemonMapper)
        val pokemonRepository = PokemonRepository.Base(
            pokemonCloudDataSource,
            cacheDataSource,
            pokemonsCloudMapper,
            pokemonsCacheMapper
        )

        val upcomingsInteractor =
            PokemonsInteractor.Base(
                pokemonRepository, BasePokemonsDataToDomainMapper(
                    BasePokemonDataToDomainMapper()
                )
            )

        val communication = PokemonCommunication.Base()
        mainViewModel = MainViewModel(
            upcomingsInteractor,
            BasePokemonsDomainToUiMapper(ResourceProvider.Base(this), BasePokemonDomainToUiMapper()),
            communication
        )
    }
}