package com.space.myapplication.di.species

import com.space.myapplication.data.species.*
import com.space.myapplication.domain.species.SpeciesRepository
import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.data.species.cache.SpeciesEntity
import com.space.myapplication.domain.species.SpeciesDomain
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.species.BaseSpeciesDomainToUiMapper
import com.space.myapplication.presentation.species.SpeciesCommunication
import com.space.myapplication.presentation.species.SpeciesUi
import dagger.Binds
import dagger.Module

@Module
interface SpeciesBindModule {
    @Binds
    fun bindSpeciesRepository(speciesRepository: BaseSpeciesRepository<SpeciesDomain>): SpeciesRepository<SpeciesDomain>

    @Binds
    fun bindSpeciesInteractor(speciesInteractor: SpeciesInteractor.Base): SpeciesInteractor

    @Binds
    fun bindSpeciesDataToDomainMapper(specieDataToDomainMapper: BaseSpeciesDataToDomainMapper): SpeciesDataToDomainMapper<SpeciesDomain>

    @Binds
    fun bindSpeciesCloudDataSource(speciesCloudDataSource: SpeciesCloudDataSource.Base): SpeciesCloudDataSource

    @Binds
    fun bindSpeciesCacheDataSource(speciesCacheDataSource: SpeciesCacheDataSource.Base): SpeciesCacheDataSource

    @Binds
    fun bindSpeciesDataToDbMapper(speciesDataToDbMapper: SpeciesDataToDbMapper.Base): SpeciesDataToDbMapper<SpeciesEntity>

    @Binds
    fun bindToSpeciesMapper(toSpeciesMapper: ToSpeciesMapper.Base): ToSpeciesMapper

    @Binds
    fun bindSpeciesDomainToUiMapper(speciesDomainToUiMapper: BaseSpeciesDomainToUiMapper): SpeciesDomainToUiMapper<SpeciesUi>

    @Binds
    fun bindSpeciesCommunication(speciesCommunication: SpeciesCommunication.Base): SpeciesCommunication
}