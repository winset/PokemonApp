package com.space.myapplication.presentation

import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper

class BasePokemonDomainToUiMapper : PokemonDomainToUiMapper {
    override fun map(name: String, url: String) = PokemonUi.Base(name, url)
}
