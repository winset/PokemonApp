package com.space.myapplication.presentation.species

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface SpeciesCommunication {
    fun map(speciesUi: SpeciesUi)
    fun observe(owner: LifecycleOwner, observer: Observer<SpeciesUi>)

    class Base : SpeciesCommunication {
        private val speciesLiveData = MutableLiveData<SpeciesUi>()

        override fun map(speciesUi: SpeciesUi) {
            speciesLiveData.value = speciesUi
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<SpeciesUi>) {
            speciesLiveData.observe(owner, observer)
        }
    }
}