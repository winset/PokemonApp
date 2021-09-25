package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.PokemonData
import com.space.myapplication.presentation.PokemonsUi
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.Exception

sealed class PokemonsDomain : Abstract.Object<PokemonsUi, PokemonsDomainToUiMapper> {

    class Success(
        private val pokemonsData: List<PokemonData>,
        private val domainMapper: BasePokemonDataToDomainMapper
    ) : PokemonsDomain() {
        override fun map(mapper: PokemonsDomainToUiMapper): PokemonsUi {
            val pokemonsDomain = pokemonsData.map { it.map(domainMapper) }
            return mapper.map(pokemonsDomain)
        }
    }

    class Fail(private val exception: Exception) : PokemonsDomain() {
        override fun map(
            mapper: PokemonsDomainToUiMapper
        ) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}