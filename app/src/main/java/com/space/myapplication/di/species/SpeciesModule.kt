package com.space.myapplication.di.species

import androidx.lifecycle.ViewModel
import com.space.myapplication.di.pokemon.ViewModelKey

import com.space.myapplication.presentation.species.SpeciesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SpeciesModule {
    @Binds
    @IntoMap
    @ViewModelKey(SpeciesViewModel::class)
    abstract fun speciesViewModel(viewModel: SpeciesViewModel): ViewModel

}