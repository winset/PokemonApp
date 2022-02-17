package com.space.myapplication.presentation.species

import com.space.myapplication.R
import com.space.myapplication.core.ErrorType
import com.space.myapplication.core.ResourceProvider
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper

class BaseSpeciesDomainToUiMapper(
    private val resourceProvider: ResourceProvider
) : SpeciesDomainToUiMapper {
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

    override fun map(errorType: ErrorType): SpeciesUi {
        val msgId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection
            ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
            else -> R.string.something_go_wrong
        }
        val message = resourceProvider.getString(msgId)
        return SpeciesUi.Fail(message)
    }

}