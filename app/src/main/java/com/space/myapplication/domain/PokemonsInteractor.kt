package com.space.myapplication.domain

import com.space.myapplication.data.PokemonsDataToDomainMapper
import com.space.myapplication.data.PokemonRepository

interface PokemonsInteractor {
    suspend fun getUpcomings(): PokemonsDomain

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val mapper: PokemonsDataToDomainMapper
    ) : PokemonsInteractor {
        override suspend fun getUpcomings() = pokemonRepository.getUpcoming().map(mapper)
    }
}