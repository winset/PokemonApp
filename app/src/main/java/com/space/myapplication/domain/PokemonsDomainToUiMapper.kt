package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.PokemonData
import com.space.myapplication.presentation.ResourceProvider
import com.space.myapplication.presentation.PokemonsUi

interface PokemonsDomainToUiMapper : Abstract.Mapper {

    fun map(upcomings: List<PokemonDomain>): PokemonsUi
    fun map(errorType: ErrorType): PokemonsUi

    class Base(
        private val resourceProvider: ResourceProvider,
        private val pokemonMapper: PokemonDomainToUiMapper
    ) : PokemonsDomainToUiMapper {
        override fun map(upcomings: List<PokemonDomain>) = PokemonsUi.Success( upcomings,pokemonMapper)
        override fun map(errorType: ErrorType) =
            PokemonsUi.Fail( errorType, resourceProvider)
    }
}