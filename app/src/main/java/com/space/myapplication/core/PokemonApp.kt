package com.space.myapplication.core

import android.app.Application
import com.space.myapplication.di.app.AppComponent
import com.space.myapplication.di.app.AppDepsStore
import com.space.myapplication.di.app.DaggerAppComponent
import io.realm.Realm

class PokemonApp : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(context = this).build()
        Realm.init(this)
        AppDepsStore.deps = appComponent
    }
}