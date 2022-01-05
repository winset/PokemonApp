package com.space.myapplication.data.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.pokemons.PokemonsDomain
import com.space.myapplication.domain.species.SpeciesDomain
import java.lang.Exception

interface SpeciesDataToDomainMapper : Abstract.Mapper {
    fun map(
        id: Int,
        isBaby: Boolean,
        isLegendary: Boolean,
        isMythical: Boolean,
        name: String,
        baseHappiness: Int,
        captureRate: Int,
        color: String,
        evolvesFromName: String,
        evolvesFromUrl: String,
        formsSwitchable: Boolean,
        genderRate: Int,
        hasGenderDifferences: Boolean,
        hatchCounter: Int,
        order: Int,
    ): SpeciesDomain
    fun map(exception: Exception): SpeciesDomain
}