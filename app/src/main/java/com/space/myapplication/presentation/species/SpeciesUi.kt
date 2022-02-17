package com.space.myapplication.presentation.species

import com.space.myapplication.core.Abstract

sealed class SpeciesUi : Abstract.Object<Unit, SpeciesUi.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    interface StringMapper : Abstract.Mapper {
        fun map(id: Int, name: String) {}
        fun map(message: String) {}
    }

    object Progress : SpeciesUi()
    class Base(
        private val id: Int,
        private val isBaby: Boolean,
        private val isLegendary: Boolean,
        private val isMythical: Boolean,
        private val name: String,
        private val baseHappiness: Int,
        private val captureRate: Int,
        private val color: String,
        private val evolvesFromName: String,
        private val evolvesFromUrl: String,
        private val formsSwitchable: Boolean,
        private val genderRate: Int,
        private val hasGenderDifferences: Boolean,
        private val hatchCounter: Int,
        private val order: Int
    ) : SpeciesUi() {
        override fun map(mapper: StringMapper) = mapper.map(id, name)
    }

    data class Fail(private val message: String) : SpeciesUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }
}