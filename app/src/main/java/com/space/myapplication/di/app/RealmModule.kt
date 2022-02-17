package com.space.myapplication.di.app

import com.space.myapplication.core.RealmProvider
import dagger.Binds
import dagger.Module

@Module
abstract class RealmModule {
    @Binds
    abstract fun bindPokemonRepository(pokemonRepository: RealmProvider.Base): RealmProvider
}