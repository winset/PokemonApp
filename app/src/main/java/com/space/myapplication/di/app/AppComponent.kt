package com.space.myapplication.di.app

import android.content.Context
import androidx.annotation.RestrictTo
import com.space.myapplication.core.RealmProvider
import com.space.myapplication.core.ResourceProvider
import com.space.myapplication.data.pokemons.cloud.PokemonService
import com.space.myapplication.data.species.cloud.SpeciesService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : AppDeps {

    override val pokemonService: PokemonService
    override val realmProvider: RealmProvider
    override val resourceProvider: ResourceProvider
    override val speciesService: SpeciesService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}

interface AppDeps {
    val pokemonService: PokemonService
    val speciesService: SpeciesService
    val realmProvider: RealmProvider
    val resourceProvider: ResourceProvider
}

interface AppDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: AppDeps

    companion object : AppDepsProvider by AppDepsStore
}

object AppDepsStore : AppDepsProvider {
    override var deps: AppDeps by Delegates.notNull()
}