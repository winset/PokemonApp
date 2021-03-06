package com.space.myapplication.domain.species

import com.space.myapplication.core.ErrorType

sealed class SpeciesDomain  {

    abstract fun <T> map(mapper: SpeciesDomainToUiMapper<T>): T

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
        private val order: Int
    ) : SpeciesDomain() {
        override fun <T> map(mapper: SpeciesDomainToUiMapper<T>): T = mapper.map(
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
        override fun <T> map(mapper: SpeciesDomainToUiMapper<T>): T = mapper.map(errorType)
    }
}