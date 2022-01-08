package com.space.myapplication.core

import android.app.Application
import com.google.gson.Gson
import com.space.myapplication.data.pokemons.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.PokemonsCloudMapper
import com.space.myapplication.data.pokemons.PokemonRepository
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.ToPokemonMapper
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cloud.PokemonService
import com.space.myapplication.data.species.SpeciesCloudDataSource
import com.space.myapplication.data.species.SpeciesRepository
import com.space.myapplication.data.species.ToSpeciesMapper
import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.data.species.cloud.SpeciesService
import com.space.myapplication.domain.pokemons.BasePokemonDataToDomainMapper
import com.space.myapplication.domain.pokemons.BasePokemonsDataToDomainMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.domain.species.BaseSpeciesDataToDomainMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.MainCommunication
import com.space.myapplication.presentation.MainViewModel
import com.space.myapplication.presentation.Navigator
import com.space.myapplication.presentation.pokemons.BasePokemonDomainToUiMapper
import com.space.myapplication.presentation.pokemons.BasePokemonsDomainToUiMapper
import com.space.myapplication.presentation.pokemons.PokemonsViewModel
import com.space.myapplication.presentation.pokemons.PokemonCommunication
import com.space.myapplication.presentation.species.SpeciesCommunication
import com.space.myapplication.presentation.species.SpeciesViewModel
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApp : Application() {

    lateinit var pokemonsViewModel: PokemonsViewModel
    lateinit var speciesViewModel: SpeciesViewModel
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

        val pokemonsInteractor =
            PokemonsInteractor.Base(
                pokemonRepository, BasePokemonsDataToDomainMapper(
                    BasePokemonDataToDomainMapper()
                )
            )
        val speciesCacheDataSource = SpeciesCacheDataSource.Base(
            RealmProvider.Base(),
            SpeciesDataToDbMapper.Base()
        )
        val speciesCloudDataSource =
            SpeciesCloudDataSource.Base(retrofit.create(SpeciesService::class.java))

        val speciesRepository =
            SpeciesRepository.Base(speciesCloudDataSource, speciesCacheDataSource,ToSpeciesMapper.Base())
        val speciesInteractor =
            SpeciesInteractor.Base(speciesRepository, BaseSpeciesDataToDomainMapper())

        val communication = PokemonCommunication.Base()
        val navigator = Navigator.Base(this)
        val navigationCommunication = MainCommunication.Base()
        pokemonsViewModel = PokemonsViewModel(
            pokemonsInteractor,
            BasePokemonsDomainToUiMapper(
                ResourceProvider.Base(this),
                BasePokemonDomainToUiMapper()
            ),
            communication,
            navigator,
            navigationCommunication
        )

        speciesViewModel = SpeciesViewModel(
            SpeciesCommunication.Base(),
            navigator
        )

        mainViewModel = MainViewModel(
            navigator,
            navigationCommunication
        )
    }
}