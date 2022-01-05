package com.space.myapplication.domain.species

import com.space.myapplication.data.species.SpeciesDataToDomainMapper
import java.lang.Exception

class BaseSpeciesDataToDomainMapper : SpeciesDataToDomainMapper {
    override fun map(
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
        order: Int
    ): SpeciesDomain = SpeciesDomain(
        id,
        isBaby,
        isLegendary,
        isMythical,
        name,
        baseHappiness,
        captureRate,
        color,
        evolvesFromName,
        evolvesFromUrl,
        formsSwitchable,
        genderRate,
        hasGenderDifferences,
        hatchCounter,
        order
    )

    override fun map(exception: Exception): SpeciesDomain {
        TODO("Not yet implemented")
    }
}