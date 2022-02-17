package com.space.myapplication.di.pokemon

import com.space.myapplication.di.app.AppDeps
import com.space.myapplication.presentation.pokemons.PokemonsFragment
import dagger.Component

@Component(
    modules = [PokemonModule::class, ViewModelModule::class, PokemonProvideModule::class, PokemonBindModule::class],
    dependencies = [AppDeps::class]
)
@PokemonScope
interface PokemonComponent {

    fun inject(pokemonsFragment: PokemonsFragment)

    @Component.Builder
    interface Builder {
        fun deps(appDeps: AppDeps): Builder
        fun build(): PokemonComponent
    }
}