package com.space.myapplication.domain.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.ErrorType
import com.space.myapplication.presentation.species.SpeciesUi

sealed class SpeciesDomain : Abstract.Object<SpeciesUi, SpeciesDomainToUiMapper> {
    data class Success(
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
        private val order: Int,
    ) : SpeciesDomain() {
        override fun map(mapper: SpeciesDomainToUiMapper) = mapper.map(
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

    data class Fail(private val errorType: ErrorType) : SpeciesDomain() {
        override fun map(mapper: SpeciesDomainToUiMapper) = mapper.map(errorType)
    }
}