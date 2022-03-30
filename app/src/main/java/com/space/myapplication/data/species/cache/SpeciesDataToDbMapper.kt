package com.space.myapplication.data.species.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper
import io.realm.RealmObject

interface SpeciesDataToDbMapper<T : RealmObject> : Abstract.Mapper {
    fun mapToDB(
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
        dbWrapper: DbWrapper<T>
    ): T

    class Base : SpeciesDataToDbMapper<SpeciesEntity> {
        override fun mapToDB(
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
            dbWrapper: DbWrapper<SpeciesEntity>
        ): SpeciesEntity {
            val speciesEntity = dbWrapper.createObject(id)
            speciesEntity.isBaby = isBaby
            speciesEntity.isLegendary = isLegendary
            speciesEntity.isMythical = isMythical
            speciesEntity.name = name
            speciesEntity.baseHappiness = baseHappiness
            speciesEntity.captureRate = captureRate
            speciesEntity.color = color
            speciesEntity.evolvesFromName = evolvesFromName
            speciesEntity.evolvesFromUrl = evolvesFromUrl
            speciesEntity.formsSwitchable = formsSwitchable
            speciesEntity.genderRate = genderRate
            speciesEntity.hasGenderDifferences = hasGenderDifferences
            speciesEntity.hatchCounter = hatchCounter
            speciesEntity.order = order
            return speciesEntity
        }
    }
}