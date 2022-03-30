package com.space.myapplication.domain.species

interface SpeciesInteractor {
    suspend fun getSpecies(name:String):SpeciesDomain

    class Base(
        private val speciesRepository: SpeciesRepository<SpeciesDomain>,
    ):SpeciesInteractor{
        override suspend fun getSpecies(name: String) = speciesRepository.getSpecies(name)
    }
}