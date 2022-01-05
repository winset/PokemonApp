package com.space.myapplication.domain.pokemons

import com.space.myapplication.data.pokemons.PokemonDataToDomainMapper

class BasePokemonDataToDomainMapper : PokemonDataToDomainMapper {
    override fun map(name: String, url: String) = PokemonDomain(name, url)
}