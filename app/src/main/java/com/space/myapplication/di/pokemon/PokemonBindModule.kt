package com.space.myapplication.di.pokemon

import com.space.myapplication.core.ErrorUiMapper
import com.space.myapplication.data.pokemons.*
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cache.PokemonEntity
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.cloud.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.cloud.PokemonsCloudMapper
import com.space.myapplication.domain.pokemons.*
import com.space.myapplication.presentation.pokemons.*
import dagger.Binds
import dagger.Module

@Module
interface PokemonBindModule {
    @Binds
    fun bindPokemonRepository(pokemonRepository: BasePokemonRepository<PokemonsDomain>): PokemonRepository<PokemonsDomain>

    @Binds
    fun bindPokemonsInteractor(pokemonsInteractor: PokemonsInteractor.Base): PokemonsInteractor

    @Binds
    fun bindPokemonsDataToDomainMapper(pokemonsDataToDomainMapper: BasePokemonsDataToDomainMapper): PokemonsDataToDomainMapper<PokemonsDomain>

    @Binds
    fun bindPokemonDataToDomainMapper(pokemonDataToDomainMapper: BasePokemonDataToDomainMapper): PokemonDataToDomainMapper<PokemonDomain>

    @Binds
    fun bindPokemonCloudDataSource(pokemonCloudDataSource: PokemonCloudDataSource.Base): PokemonCloudDataSource

    @Binds
    fun bindPokemonCacheDataSource(pokemonCacheDataSource: PokemonCacheDataSource.Base): PokemonCacheDataSource

    @Binds
    fun bindPokemonDataToDbMapper(pokemonDataToDbMapper: PokemonDataToDbMapper.Base): PokemonDataToDbMapper<PokemonEntity>

    @Binds
    fun bindPokemonsCloudMapper(pokemonsCloudMapper: PokemonsCloudMapper.Base): PokemonsCloudMapper

    @Binds
    fun bindPokemonsCacheMapper(pokemonsCacheMapper: PokemonsCacheMapper.Base): PokemonsCacheMapper

    @Binds
    fun bindToPokemonMapper(toPokemonMapper: ToPokemonMapper.Base): ToPokemonMapper

    @Binds
    fun bindPokemonsDomainToUiMapper(pokemonsDomainToUiMapper: BasePokemonsDomainToUiMapper): PokemonsDomainToUiMapper<PokemonsUi>

    @Binds
    fun bindPokemonDomainToUiMapper(pokemonDomainToUiMapper: BasePokemonDomainToUiMapper): PokemonDomainToUiMapper<PokemonUi>

    @Binds
    fun bindPokemonCommunication(pokemonCommunication: PokemonCommunication.Base): PokemonCommunication
}