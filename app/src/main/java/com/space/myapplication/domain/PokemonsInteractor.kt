package com.space.myapplication.domain

import com.space.myapplication.data.PokemonsDataToDomainMapper
import com.space.myapplication.data.PokemonRepository

interface PokemonsInteractor {
    suspend fun getPokemons(): PokemonsDomain

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val mapper: PokemonsDataToDomainMapper
    ) : PokemonsInteractor {
        override suspend fun getPokemons() = pokemonRepository.getPokemon().map(mapper)
    }
}