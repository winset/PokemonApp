package com.space.myapplication.data.pokemons

import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.cloud.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.cloud.PokemonsCloudMapper
import com.space.myapplication.domain.pokemons.PokemonRepository

class BasePokemonRepository<T>(
    private val pokemonCloudDataSource: PokemonCloudDataSource,
    private val cacheDataSource: PokemonCacheDataSource,
    private val pokemonsCloudMapper: PokemonsCloudMapper,
    private val pokemonsCacheMapper: PokemonsCacheMapper,
    private val mapper: PokemonsDataToDomainMapper<T>
) : PokemonRepository<T> {
    override suspend fun getPokemon(page: Int): T {
        val dataObject = try {
            val pokemonEntityList = cacheDataSource.getPokemonList(page)
            if (pokemonEntityList.isEmpty()) {
                val upcomingCloudList = pokemonCloudDataSource.getPokemon(page)
                val upcomingList = pokemonsCloudMapper.map(upcomingCloudList)
                cacheDataSource.savePokemonList(upcomingList, page)
                PokemonsData.Success(upcomingList)
            } else {
                PokemonsData.Success(pokemonsCacheMapper.map(pokemonEntityList))
            }
        } catch (exception: Exception) {
            PokemonsData.Fail(exception)
        }
        return dataObject.map(mapper)
    }
}