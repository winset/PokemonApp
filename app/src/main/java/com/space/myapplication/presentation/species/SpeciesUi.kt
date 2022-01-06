package com.space.myapplication.presentation.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.pokemons.PokemonUi

sealed class SpeciesUi : Abstract.Object<Unit, SpeciesUi.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    interface StringMapper : Abstract.Mapper {
        fun map(id: Int, name: String) {}
        fun map(message: String) {}
    }

    object Progress : SpeciesUi()
    class Base(
        private val id: Int,
        private val name: String
    ) : SpeciesUi() {
        override fun map(mapper: StringMapper) = mapper.map(id, name)
    }

    data class Fail(private val message: String) : SpeciesUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }
}