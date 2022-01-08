package com.space.myapplication.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.space.myapplication.presentation.pokemons.PokemonUi

interface Communication<T> : Abstract.Mapper.Data<T, Unit> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T> : Communication<T> {
        private val listLiveData = MutableLiveData<T>()

        override fun map(data: T) {
            listLiveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            listLiveData.observe(owner, observer)
        }
    }
}