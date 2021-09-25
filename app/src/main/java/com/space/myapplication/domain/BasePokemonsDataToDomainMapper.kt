package com.space.myapplication.domain


import com.space.myapplication.data.PokemonData
import com.space.myapplication.data.PokemonsDataToDomainMapper
import java.lang.Exception

class BasePokemonsDataToDomainMapper(private val pokemonMapper: BasePokemonDataToDomainMapper) :
    PokemonsDataToDomainMapper {
    override fun map(upcomings: List<PokemonData>) =
        PokemonsDomain.Success(upcomings, pokemonMapper)

    override fun map(exception: Exception) = PokemonsDomain.Fail(exception)
}