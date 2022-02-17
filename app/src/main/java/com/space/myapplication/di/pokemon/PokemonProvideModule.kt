package com.space.myapplication.di.pokemon

import com.space.myapplication.core.RealmProvider
import com.space.myapplication.core.ResourceProvider
import com.space.myapplication.data.pokemons.*
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.cloud.PokemonService
import com.space.myapplication.domain.pokemons.BasePokemonDataToDomainMapper
import com.space.myapplication.domain.pokemons.BasePokemonsDataToDomainMapper
import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.presentation.pokemons.BasePokemonDomainToUiMapper
import com.space.myapplication.presentation.pokemons.BasePokemonsDomainToUiMapper
import com.space.myapplication.presentation.pokemons.PokemonCommunication
import dagger.Module
import dagger.Provides

@Module
class PokemonProvideModule {
    @Provides
    fun providePokemonRepository(
        pokemonCloudDataSource: PokemonCloudDataSource,
        cacheDataSource: PokemonCacheDataSource,
        pokemonsCloudMapper: PokemonsCloudMapper,
        pokemonsCacheMapper: PokemonsCacheMapper
    ): PokemonRepository.Base {
        return PokemonRepository.Base(
            pokemonCloudDataSource,
            cacheDataSource,
            pokemonsCloudMapper,
            pokemonsCacheMapper
        )
    }

    @Provides
    fun providePokemonsInteractor(
        pokemonRepository: PokemonRepository,
        mapper: PokemonsDataToDomainMapper
    ): PokemonsInteractor.Base {
        return PokemonsInteractor.Base(pokemonRepository, mapper)
    }

    @Provides
    fun providePokemonsDataToDomainMapper(pokemonMapper: PokemonDataToDomainMapper): BasePokemonsDataToDomainMapper {
        return BasePokemonsDataToDomainMapper(pokemonMapper)
    }

    @Provides
    fun providePokemonDataToDomainMapper(): BasePokemonDataToDomainMapper {
        return BasePokemonDataToDomainMapper()
    }

    @Provides
    fun providePokemonCloudDataSource(service: PokemonService): PokemonCloudDataSource.Base {
        return PokemonCloudDataSource.Base(service)
    }

    @Provides
    fun providePokemonCacheDataSource(
        realmProvider: RealmProvider,
        pokemonDataToDbMapper: PokemonDataToDbMapper
    ): PokemonCacheDataSource.Base {
        return PokemonCacheDataSource.Base(realmProvider, pokemonDataToDbMapper)
    }

    @Provides
    fun providePokemonDataToDbMapper(): PokemonDataToDbMapper.Base {
        return PokemonDataToDbMapper.Base()
    }

    @Provides
    fun providePokemonsCloudMapper(toPokemonMapper: ToPokemonMapper): PokemonsCloudMapper.Base {
        return PokemonsCloudMapper.Base(toPokemonMapper)
    }

    @Provides
    fun providePokemonsCacheMapper(pokemonCacheMapper: ToPokemonMapper): PokemonsCacheMapper.Base {
        return PokemonsCacheMapper.Base(pokemonCacheMapper)
    }

    @Provides
    fun provideToPokemonMapper(): ToPokemonMapper.Base {
        return ToPokemonMapper.Base()
    }

    @Provides
    fun providePokemonsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        pokemonMapper: PokemonDomainToUiMapper
    ): BasePokemonsDomainToUiMapper {
        return BasePokemonsDomainToUiMapper(resourceProvider, pokemonMapper)
    }

    @Provides
    fun providePokemonDomainToUiMapper(): BasePokemonDomainToUiMapper {
        return BasePokemonDomainToUiMapper()
    }

    @Provides
    fun providePokemonCommunication(): PokemonCommunication.Base {
        return PokemonCommunication.Base()
    }

}