package com.space.myapplication.data

import com.space.myapplication.data.cache.PokemonCacheDataSource
import com.space.myapplication.data.cache.PokemonsCacheMapper
import kotlinx.coroutines.delay
import java.lang.Exception

interface PokemonRepository {
    suspend fun getPokemon(page:Int): PokemonsData

    class Base(
        private val pokemonCloudDataSource: PokemonCloudDataSource,
        private val cacheDataSource: PokemonCacheDataSource,
        private val pokemonsCloudMapper: PokemonsCloudMapper,
        private val pokemonsCacheMapper: PokemonsCacheMapper
    ) : PokemonRepository {
        override suspend fun getPokemon(page:Int) = try {
            delay(1000)//todo just for test
            val upcomingCacheList = cacheDataSource.getPokemonList(page)
            if (upcomingCacheList.isEmpty()) {
                val upcomingCloudList = pokemonCloudDataSource.getPokemon(page)
                val upcomingList = pokemonsCloudMapper.map(upcomingCloudList)
                cacheDataSource.savePokemonList(upcomingList,page)
                PokemonsData.Success(upcomingList)
            } else {
                PokemonsData.Success(pokemonsCacheMapper.map(upcomingCacheList))
            }
        } catch (exception: Exception) {
            PokemonsData.Fail(exception)
        }
    }
}