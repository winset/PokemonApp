package com.space.myapplication.di.species

import com.space.myapplication.data.species.SpeciesCloudDataSource
import com.space.myapplication.data.species.SpeciesDataToDomainMapper
import com.space.myapplication.data.species.SpeciesRepository
import com.space.myapplication.data.species.ToSpeciesMapper
import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.domain.species.BaseSpeciesDataToDomainMapper
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.species.BaseSpeciesDomainToUiMapper
import com.space.myapplication.presentation.species.SpeciesCommunication
import dagger.Binds
import dagger.Module

@Module
interface SpeciesBindModule {
    @Binds
    fun bindSpeciesRepository(speciesRepository: SpeciesRepository.Base): SpeciesRepository

    @Binds
    fun bindSpeciesInteractor(speciesInteractor: SpeciesInteractor.Base): SpeciesInteractor

    @Binds
    fun bindSpeciesDataToDomainMapper(specieDataToDomainMapper: BaseSpeciesDataToDomainMapper): SpeciesDataToDomainMapper

    @Binds
    fun bindSpeciesCloudDataSource(speciesCloudDataSource: SpeciesCloudDataSource.Base): SpeciesCloudDataSource

    @Binds
    fun bindSpeciesCacheDataSource(speciesCacheDataSource: SpeciesCacheDataSource.Base): SpeciesCacheDataSource

    @Binds
    fun bindSpeciesDataToDbMapper(speciesDataToDbMapper: SpeciesDataToDbMapper.Base): SpeciesDataToDbMapper

    @Binds
    fun bindToSpeciesMapper(toSpeciesMapper: ToSpeciesMapper.Base): ToSpeciesMapper

    @Binds
    fun bindSpeciesDomainToUiMapper(speciesDomainToUiMapper: BaseSpeciesDomainToUiMapper): SpeciesDomainToUiMapper

    @Binds
    fun bindSpeciesCommunication(speciesCommunication: SpeciesCommunication.Base): SpeciesCommunication
}