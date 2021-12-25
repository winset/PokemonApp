package com.space.myapplication.domain

import com.space.myapplication.data.PokemonsDataToDomainMapper
import com.space.myapplication.data.PokemonRepository

interface PokemonsInteractor {
    suspend fun getPokemons(page:Int): PokemonsDomain

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val mapper: PokemonsDataToDomainMapper
    ) : PokemonsInteractor {
        override suspend fun getPokemons(page:Int) = pokemonRepository.getPokemon(page).map(mapper)
    }
}