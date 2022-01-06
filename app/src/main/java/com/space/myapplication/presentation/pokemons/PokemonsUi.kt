package com.space.myapplication.presentation.pokemons

import com.space.myapplication.core.Abstract

sealed class PokemonsUi : Abstract.Object<Unit, PokemonCommunication> {

    data class Base(
        private val pokemons: List<PokemonUi>
    ) : PokemonsUi() {
        override fun map(mapper: PokemonCommunication) = mapper.map(pokemons)
    }
}