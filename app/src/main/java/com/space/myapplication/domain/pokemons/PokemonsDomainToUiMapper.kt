package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.ErrorType

interface PokemonsDomainToUiMapper<T> : Abstract.Mapper {
    fun map(pokemons: List<PokemonDomain>): T
    fun map(errorType: ErrorType): T
}