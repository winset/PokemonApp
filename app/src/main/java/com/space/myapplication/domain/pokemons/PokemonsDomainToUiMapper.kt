package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.pokemons.ErrorType
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.presentation.PokemonsUi

interface PokemonsDomainToUiMapper : Abstract.Mapper {
    fun map(pokemons: List<PokemonDomain>): PokemonsUi
    fun map(errorType: ErrorType): PokemonsUi
}