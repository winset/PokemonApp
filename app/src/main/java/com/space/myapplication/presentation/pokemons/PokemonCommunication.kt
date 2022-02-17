package com.space.myapplication.presentation.pokemons

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.space.myapplication.core.Communication

interface PokemonCommunication : Communication<List<PokemonUi>> {
    class Base : PokemonCommunication {
        private val listLiveData = MutableLiveData<List<PokemonUi>>()
        private val listPokemon = mutableListOf<PokemonUi>()

        override fun map(data: List<PokemonUi>) {
            if (data.firstOrNull() is PokemonUi.Base) {
                listPokemon.addAll(data)
                listLiveData.value = listPokemon
            } else {
                listLiveData.value = data
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>) {
            listLiveData.observe(owner, observer)
        }
    }
}