package com.space.myapplication.di.app

import android.content.Context
import com.space.myapplication.core.RealmProvider
import com.space.myapplication.core.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RealmModule::class])
class AppModule {

    @Provides
    fun provideResourceManager(context: Context): ResourceProvider = ResourceProvider.Base(context)

    @Provides
    fun provideRealmProviderBase(): RealmProvider.Base {
        return RealmProvider.Base()
    }

}