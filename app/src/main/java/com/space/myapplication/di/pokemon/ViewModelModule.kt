package com.space.myapplication.di.pokemon

import androidx.lifecycle.ViewModelProvider
import com.space.myapplication.di.app.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}