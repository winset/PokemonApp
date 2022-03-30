package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.ErrorType

sealed class PokemonsDomain {
    abstract fun <T> map(mapper: PokemonsDomainToUiMapper<T>): T

    data class Success(private val pokemonsData: List<PokemonDomain>) : PokemonsDomain() {
        override fun <T> map(mapper: PokemonsDomainToUiMapper<T>): T = mapper.map(pokemonsData)
    }

    data class Fail(private val errorType: ErrorType) : PokemonsDomain() {
        override fun <T> map(mapper: PokemonsDomainToUiMapper<T>): T = mapper.map(errorType)
    }
}