package com.space.myapplication.di.pokemon

import androidx.lifecycle.ViewModel
import com.space.myapplication.presentation.pokemons.PokemonsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PokemonModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokemonsViewModel::class)
    abstract fun pokemonViewModel(viewModel: PokemonsViewModel): ViewModel

}