package com.space.myapplication.domain.species

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.ErrorType
import com.space.myapplication.presentation.species.SpeciesUi

interface SpeciesDomainToUiMapper :Abstract.Mapper{
    fun map( id: Int,
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
             order: Int,): SpeciesUi
    fun map(errorType: ErrorType): SpeciesUi
}