package com.space.myapplication.data.species

import com.space.myapplication.core.Abstract

interface ToSpeciesMapper : Abstract.Mapper {
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
    ): SpeciesData.Success

    class Base : ToSpeciesMapper {
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
        ): SpeciesData.Success = SpeciesData.Success(
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
    }
}