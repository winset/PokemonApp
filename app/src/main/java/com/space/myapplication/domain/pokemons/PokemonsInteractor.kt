package com.space.myapplication.domain.pokemons

import com.space.myapplication.data.pokemons.PokemonsDataToDomainMapper
import com.space.myapplication.data.pokemons.PokemonRepository

interface PokemonsInteractor {
    suspend fun getPokemons(page:Int): PokemonsDomain

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val mapper: PokemonsDataToDomainMapper
    ) : PokemonsInteractor {
        override suspend fun getPokemons(page:Int) = pokemonRepository.getPokemon(page).map(mapper)
    }
}