package com.space.myapplication.domain.species

import com.space.myapplication.data.species.SpeciesDataToDomainMapper
import com.space.myapplication.data.species.SpeciesRepository

interface SpeciesInteractor {
    suspend fun getSpecies(name:String):SpeciesDomain

    class Base(
        private val speciesRepository: SpeciesRepository,
        private val mapper: SpeciesDataToDomainMapper
    ):SpeciesInteractor{
        override suspend fun getSpecies(name: String) = speciesRepository.getSpecies(name).map(mapper)
    }
}