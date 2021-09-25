package com.space.myapplication.domain

import com.space.myapplication.data.PokemonDataToDomainMapper

class BasePokemonDataToDomainMapper : PokemonDataToDomainMapper {
    override fun map(name: String, url: String) = PokemonDomain(name, url)
}