package com.space.myapplication.presentation

import com.space.myapplication.R
import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.BasePokemonDataToDomainMapper
import com.space.myapplication.domain.ErrorType
import com.space.myapplication.domain.PokemonDomain
import com.space.myapplication.domain.PokemonDomainToUiMapper

sealed class PokemonsUi : Abstract.Object<Unit, UpcomingCommunication> {

    class Success(
        private val upcoming: List<PokemonDomain>,
        private val pokemonMapper: PokemonDomainToUiMapper
    ) : PokemonsUi() {
        override fun map(mapper: UpcomingCommunication) {
            val upcomingsUi  = upcoming.map { it.map(pokemonMapper) }
            mapper.map(upcomingsUi)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : PokemonsUi() {
        override fun map(mapper: UpcomingCommunication) {
            val msgId = when (errorType) { // todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
                else -> R.string.something_go_wrong
            }
            val message = resourceProvider.getString(msgId)
            mapper.map(listOf(PokemonUi.Fail(message)))
        }
    }
}