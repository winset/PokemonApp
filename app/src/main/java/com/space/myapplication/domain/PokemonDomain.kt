package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.PokemonUi

data class PokemonDomain(
    private val name: String,
    private val url: String
) : Abstract.Object<PokemonUi, PokemonDomainToUiMapper> {
    override fun map(mapper: PokemonDomainToUiMapper) = mapper.map(name, url)
}
