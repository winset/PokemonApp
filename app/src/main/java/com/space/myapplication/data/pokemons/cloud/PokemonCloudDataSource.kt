package com.space.myapplication.data.pokemons.cloud

interface PokemonCloudDataSource {
    suspend fun getPokemon(page: Int): List<PokemonDto>

    class Base(private val service: PokemonService) : PokemonCloudDataSource {
        override suspend fun getPokemon(page: Int): List<PokemonDto> {
            return service.getPokemon(offset = (page) * 20).results
        }
    }
}