package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.pokemons.PokemonUi

data class PokemonDomain(
    private val name: String,
    private val url: String
) : Abstract.Object<PokemonUi, PokemonDomainToUiMapper> {
    override fun map(mapper: PokemonDomainToUiMapper) = mapper.map(name, url)
}