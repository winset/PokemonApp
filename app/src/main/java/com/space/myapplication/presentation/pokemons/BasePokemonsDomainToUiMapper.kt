package com.space.myapplication.presentation.pokemons

import com.space.myapplication.core.ErrorType
import com.space.myapplication.core.ErrorUiMapper
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper

class BasePokemonsDomainToUiMapper(
    private val errorMapper: ErrorUiMapper,
    private val pokemonMapper: PokemonDomainToUiMapper<PokemonUi>
) : PokemonsDomainToUiMapper<PokemonsUi> {
    override fun map(pokemons: List<PokemonDomain>) =
        PokemonsUi.Base(pokemons.map { it.map(pokemonMapper) })

    override fun map(errorType: ErrorType) =
        PokemonsUi.Base(listOf(PokemonUi.Fail(errorMapper.map(errorType))))
}