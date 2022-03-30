package com.space.myapplication.data.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import io.realm.RealmObject

sealed class SpeciesData : Abstract.DataObject {

    abstract fun <T> map(mapper: SpeciesDataToDomainMapper<T>): T

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
    ) : SpeciesData() , ToSpeciesDb{
        override fun <T> map(mapper: SpeciesDataToDomainMapper<T>): T = mapper.map(
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

        override fun <T : RealmObject> map(mapper: SpeciesDataToDbMapper<T>, db: DbWrapper<T>): T = mapper.mapToDB(
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
            db
        )
    }

    data class Fail(private val exception: Exception) : SpeciesData() {
        override fun <T> map(mapper: SpeciesDataToDomainMapper<T>): T {
            return mapper.map(exception)
        }
    }
}

interface ToSpeciesDb {
    fun <T : RealmObject> map(mapper: SpeciesDataToDbMapper<T>, db: DbWrapper<T>): T
}