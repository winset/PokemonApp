package com.space.myapplication.di.pokemon

import com.space.myapplication.data.pokemons.*
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.domain.pokemons.*
import com.space.myapplication.presentation.pokemons.BasePokemonDomainToUiMapper
import com.space.myapplication.presentation.pokemons.BasePokemonsDomainToUiMapper
import com.space.myapplication.presentation.pokemons.PokemonCommunication
import dagger.Binds
import dagger.Module

@Module
interface PokemonBindModule {
    @Binds
    fun bindPokemonRepository(pokemonRepository: PokemonRepository.Base): PokemonRepository

    @Binds
     fun bindPokemonsInteractor(pokemonsInteractor: PokemonsInteractor.Base): PokemonsInteractor

    @Binds
     fun bindPokemonsDataToDomainMapper(pokemonsDataToDomainMapper: BasePokemonsDataToDomainMapper): PokemonsDataToDomainMapper

    @Binds
     fun bindPokemonDataToDomainMapper(pokemonDataToDomainMapper: BasePokemonDataToDomainMapper): PokemonDataToDomainMapper

    @Binds
     fun bindPokemonCloudDataSource(pokemonCloudDataSource: PokemonCloudDataSource.Base): PokemonCloudDataSource

    @Binds
     fun bindPokemonCacheDataSource(pokemonCacheDataSource: PokemonCacheDataSource.Base): PokemonCacheDataSource

    @Binds
     fun bindPokemonDataToDbMapper(pokemonDataToDbMapper: PokemonDataToDbMapper.Base): PokemonDataToDbMapper

    @Binds
     fun bindPokemonsCloudMapper(pokemonsCloudMapper: PokemonsCloudMapper.Base): PokemonsCloudMapper

    @Binds
     fun bindPokemonsCacheMapper(pokemonsCacheMapper: PokemonsCacheMapper.Base): PokemonsCacheMapper

    @Binds
     fun bindToPokemonMapper(toPokemonMapper: ToPokemonMapper.Base): ToPokemonMapper

    @Binds
     fun bindPokemonsDomainToUiMapper(pokemonsDomainToUiMapper: BasePokemonsDomainToUiMapper): PokemonsDomainToUiMapper

    @Binds
     fun bindPokemonDomainToUiMapper(pokemonDomainToUiMapper: BasePokemonDomainToUiMapper): PokemonDomainToUiMapper

    @Binds
     fun bindPokemonCommunication(pokemonCommunication: PokemonCommunication.Base): PokemonCommunication
}