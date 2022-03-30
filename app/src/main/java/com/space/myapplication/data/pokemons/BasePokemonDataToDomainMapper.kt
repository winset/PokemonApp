package com.space.myapplication.data.pokemons

import com.space.myapplication.domain.pokemons.PokemonDomain

class BasePokemonDataToDomainMapper : PokemonDataToDomainMapper<PokemonDomain> {
    override fun map(name: String, url: String) = PokemonDomain(name, url)
}