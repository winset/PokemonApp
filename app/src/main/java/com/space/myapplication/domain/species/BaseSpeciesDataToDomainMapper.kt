package com.space.myapplication.domain.species

import com.space.myapplication.data.species.SpeciesDataToDomainMapper
import com.space.myapplication.domain.ErrorType
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

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
    ): SpeciesDomain = SpeciesDomain.Success(
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

    override fun map(exception: Exception) = SpeciesDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}