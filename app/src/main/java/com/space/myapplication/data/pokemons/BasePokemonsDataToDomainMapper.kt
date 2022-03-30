package com.space.myapplication.data.pokemons

import com.space.myapplication.core.ErrorType
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.domain.pokemons.PokemonsDomain
import retrofit2.HttpException
import java.net.UnknownHostException

class BasePokemonsDataToDomainMapper(
    private val pokemonMapper: PokemonDataToDomainMapper<PokemonDomain>
) : PokemonsDataToDomainMapper<PokemonsDomain> {

    override fun map(data: List<PokemonData>): PokemonsDomain {
        val domainPokemons = data.map { it.map(pokemonMapper) }
        return PokemonsDomain.Success(domainPokemons)
    }

    override fun map(e: Exception) = PokemonsDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}