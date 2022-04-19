package com.space.myapplication.presentation.species

import com.space.myapplication.core.ErrorType
import com.space.myapplication.core.ErrorUiMapper
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper

class BaseSpeciesDomainToUiMapper(
    private val errorMapper: ErrorUiMapper
) : SpeciesDomainToUiMapper<SpeciesUi> {
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
    ) = SpeciesUi.Base(
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

    override fun map(errorType: ErrorType): SpeciesUi = SpeciesUi.Fail(errorMapper.map(errorType))
}