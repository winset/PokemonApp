package com.space.myapplication.data.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.data.species.cache.SpeciesEntity
import com.space.myapplication.domain.species.SpeciesDomain

sealed class SpeciesData : Abstract.Object<SpeciesDomain, SpeciesDataToDomainMapper> {

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
    ) : SpeciesData() , ToSpeciesDb<SpeciesEntity, SpeciesDataToDbMapper>{
        override fun map(mapper: SpeciesDataToDomainMapper) = mapper.map(
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

        override fun mapTo(
            mapper: SpeciesDataToDbMapper,
            dbWrapper: DbWrapper<SpeciesEntity>
        ): SpeciesEntity = mapper.mapToDB(
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
            order,
            dbWrapper
        )
    }

    data class Fail(private val exception: Exception) : SpeciesData() {
        override fun map(mapper: SpeciesDataToDomainMapper): SpeciesDomain {
            return mapper.map(exception)
        }
    }

}

interface ToSpeciesDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, dbWrapper: DbWrapper<T>): T
}