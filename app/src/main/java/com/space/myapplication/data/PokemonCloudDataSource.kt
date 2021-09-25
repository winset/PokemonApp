package com.space.myapplication.data

import com.space.myapplication.data.net.PokemonDto
import com.space.myapplication.data.net.PokemonService

interface PokemonCloudDataSource {
    suspend fun getPokemon(): List<PokemonDto>

    class Base(private val service: PokemonService) : PokemonCloudDataSource {
        override suspend fun getPokemon(): List<PokemonDto> {
            return service.getPokemon().results
        }
    }
}