package com.space.myapplication.di.species

import com.space.myapplication.core.RealmProvider
import com.space.myapplication.core.ResourceProvider
import com.space.myapplication.data.species.SpeciesCloudDataSource
import com.space.myapplication.data.species.SpeciesDataToDomainMapper
import com.space.myapplication.data.species.SpeciesRepository
import com.space.myapplication.data.species.ToSpeciesMapper
import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.data.species.cache.SpeciesDataToDbMapper
import com.space.myapplication.data.species.cloud.SpeciesService
import com.space.myapplication.domain.species.BaseSpeciesDataToDomainMapper
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
        toSpeciesMapper: ToSpeciesMapper
    ) = SpeciesRepository.Base(
        speciesCloudDataSource,
        cacheDataSource,
        toSpeciesMapper
    )

    @Provides
    fun provideSpeciesInteractor(
        speciesRepository: SpeciesRepository,
        mapper: SpeciesDataToDomainMapper
    ) = SpeciesInteractor.Base(speciesRepository, mapper)

    @Provides
    fun provideSpeciesDataToDomainMapper() = BaseSpeciesDataToDomainMapper()

    @Provides
    fun provideSpeciesCloudDataSource(service: SpeciesService) =
        SpeciesCloudDataSource.Base(service)

    @Provides
    fun provideSpeciesCacheDataSource(
        realmProvider: RealmProvider,
        speciesDataToDbMapper: SpeciesDataToDbMapper
    ) = SpeciesCacheDataSource.Base(realmProvider, speciesDataToDbMapper)

    @Provides
    fun provideSpeciesDataToDbMapper() = SpeciesDataToDbMapper.Base()

    @Provides
    fun provideSpeciesCloudMapper() = ToSpeciesMapper.Base()

    @Provides
    fun provideSpeciesDomainToUiMapper(resourceProvider: ResourceProvider) =
        BaseSpeciesDomainToUiMapper(resourceProvider)

    @Provides
    fun provideSpeciesCommunication() = SpeciesCommunication.Base()
}