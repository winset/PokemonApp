package com.space.myapplication.presentation.pokemons

import com.space.myapplication.R
import com.space.myapplication.domain.ErrorType
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.presentation.ResourceProvider

class BasePokemonsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val pokemonMapper: PokemonDomainToUiMapper
) : PokemonsDomainToUiMapper {
    override fun map(pokemons: List<PokemonDomain>) =
        PokemonsUi.Base(pokemons.map { it.map(pokemonMapper) })

    override fun map(errorType: ErrorType): PokemonsUi {
        val msgId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection
            ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
            else -> R.string.something_go_wrong
        }
        val message = resourceProvider.getString(msgId)
        return PokemonsUi.Base(listOf(PokemonUi.Fail(message)))
    }

}