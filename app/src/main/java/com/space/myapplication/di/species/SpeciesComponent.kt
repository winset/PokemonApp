package com.space.myapplication.di.species

import com.space.myapplication.di.app.AppDeps
import com.space.myapplication.di.pokemon.ViewModelModule
import com.space.myapplication.presentation.species.SpeciesFragment
import dagger.Component

@Component(
    modules = [SpeciesModule::class, ViewModelModule::class, SpeciesProvideModule::class, SpeciesBindModule::class],
    dependencies = [AppDeps::class]
)
@SpeciesScope
interface SpeciesComponent {

    fun inject(pokemonsFragment: SpeciesFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: AppDeps): Builder
        fun build(): SpeciesComponent
    }

}