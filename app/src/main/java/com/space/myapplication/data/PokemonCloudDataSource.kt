package com.space.myapplication.data

import com.space.myapplication.data.net.PokemonDto
import com.space.myapplication.data.net.PokemonService

interface PokemonCloudDataSource {
    suspend fun getPokemon(page: Int): List<PokemonDto>

    class Base(private val service: PokemonService) : PokemonCloudDataSource {
        override suspend fun getPokemon(page: Int): List<PokemonDto> {
            return service.getPokemon(offset = (page+1) * 20).results
        }
    }
}