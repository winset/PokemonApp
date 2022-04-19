package com.space.myapplication.di.species

import com.space.myapplication.core.ErrorUiMapper
import com.space.myapplication.core.RealmProvider
import com.space.myapplication.core.ResourceProvider
import com.space.myapplication.data.species.*
import com.space.myapplication.domain.species.SpeciesRepository
import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.data.species.cache.SpeciesEntity
import com.space.myapplication.data.species.cloud.SpeciesService
import com.space.myapplication.domain.species.SpeciesDomain
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.species.BaseSpeciesDomainToUiMapper
import com.space.myapplication.presentation.species.SpeciesCommunication
import dagger.Module
import dagger.Provides

@Module
class SpeciesProvideModule {
    @Provides
    fun provideSpeciesRepository(
        speciesCloudDataSource: SpeciesCloudDataSource,
        cacheDataSource: SpeciesCacheDataSource,
        toSpeciesMapper: ToSpeciesMapper,
        mapper: SpeciesDataToDomainMapper<SpeciesDomain>
    ) = BaseSpeciesRepository(
        speciesCloudDataSource,
        cacheDataSource,
        toSpeciesMapper,
        mapper
    )

    @Provides
    fun provideSpeciesInteractor(
        speciesRepository: SpeciesRepository<SpeciesDomain>,
    ) = SpeciesInteractor.Base(speciesRepository)

    @Provides
    fun provideSpeciesDataToDomainMapper() = BaseSpeciesDataToDomainMapper()

    @Provides
    fun provideSpeciesCloudDataSource(service: SpeciesService) =
        SpeciesCloudDataSource.Base(service)

    @Provides
    fun provideSpeciesCacheDataSource(
        realmProvider: RealmProvider,
        speciesDataToDbMapper: SpeciesDataToDbMapper<SpeciesEntity>
    ) = SpeciesCacheDataSource.Base(realmProvider, speciesDataToDbMapper)

    @Provides
    fun provideSpeciesDataToDbMapper() = SpeciesDataToDbMapper.Base()

    @Provides
    fun provideSpeciesCloudMapper() = ToSpeciesMapper.Base()

    @Provides
    fun provideSpeciesDomainToUiMapper(errorUiMapper: ErrorUiMapper) =
        BaseSpeciesDomainToUiMapper(errorUiMapper)

    @Provides
    fun provideSpeciesCommunication() = SpeciesCommunication.Base()
}