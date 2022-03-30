package com.space.myapplication.presentation.pokemons

import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper

class BasePokemonDomainToUiMapper : PokemonDomainToUiMapper<PokemonUi> {
    override fun map(name: String, url: String) = PokemonUi.Base(name, url)
}
