package com.space.myapplication.domain


import com.space.myapplication.data.PokemonData
import com.space.myapplication.data.PokemonDataToDomainMapper
import com.space.myapplication.data.PokemonsDataToDomainMapper
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class BasePokemonsDataToDomainMapper(private val pokemonMapper: PokemonDataToDomainMapper) :
    PokemonsDataToDomainMapper {
    override fun map(pokemons: List<PokemonData>): PokemonsDomain {
        val domainPokemons = pokemons.map { it.map(pokemonMapper) }
        return PokemonsDomain.Success(domainPokemons)
    }

    override fun map(exception: Exception) = PokemonsDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}