package com.space.myapplication.data.species.cloud

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.species.SpeciesData
import com.space.myapplication.data.species.ToSpeciesMapper

data class SpeciesDto(
    val id: Int,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val name: String,
    val baseHappiness: Int,
    val captureRate: Int,
    val colorDto: ColorDto,
    val evolvesFromDto: EvolvesFromSpeciesDto,
    val formsSwitchable: Boolean,
    val genderRate: Int,
    val hasGenderDifferences: Boolean,
    val hatchCounter: Int,
    val order: Int,
) : Abstract.Object<SpeciesData, ToSpeciesMapper> {
    override fun map(mapper: ToSpeciesMapper) = mapper.map(
        id,
        isBaby,
        isLegendary,
        isMythical,
        name,
        baseHappiness,
        captureRate,
        colorDto.name,
        evolvesFromDto.name,
        evolvesFromDto.url,
        formsSwitchable,
        genderRate,
        hasGenderDifferences,
        hatchCounter,
        order
    )
}