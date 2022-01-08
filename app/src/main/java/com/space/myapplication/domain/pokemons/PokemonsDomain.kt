package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.ErrorType
import com.space.myapplication.presentation.pokemons.PokemonsUi

sealed class PokemonsDomain : Abstract.Object<PokemonsUi, PokemonsDomainToUiMapper> {

    data class Success(private val pokemonsData: List<PokemonDomain>) : PokemonsDomain() {
        override fun map(mapper: PokemonsDomainToUiMapper) = mapper.map(pokemonsData)
    }

    data class Fail(private val errorType: ErrorType) : PokemonsDomain() {
        override fun map(mapper: PokemonsDomainToUiMapper) = mapper.map(errorType)
    }
}