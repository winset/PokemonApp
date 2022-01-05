package com.space.myapplication.data.species.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.species.SpeciesData
import com.space.myapplication.data.species.ToSpeciesMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SpeciesEntity : RealmObject(), Abstract.Object<SpeciesData, ToSpeciesMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var isBaby: Boolean = false
    var isLegendary: Boolean = false
    var isMythical: Boolean = false
    var baseHappiness: Int = -1
    var captureRate: Int = -1
    var color: String = ""
    var evolvesFromName: String = ""
    var evolvesFromUrl: String = ""
    var formsSwitchable: Boolean = false
    var genderRate: Int = -1
    var hasGenderDifferences: Boolean = false
    var hatchCounter: Int = -1
    var order: Int = -1

    override fun map(mapper: ToSpeciesMapper) = mapper.map(
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