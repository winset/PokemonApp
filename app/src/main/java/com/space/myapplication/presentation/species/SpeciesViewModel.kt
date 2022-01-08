package com.space.myapplication.presentation.species

import androidx.lifecycle.ViewModel
import com.space.myapplication.presentation.Navigator
import com.space.myapplication.presentation.Screen

class SpeciesViewModel(
    private val communication: SpeciesCommunication,
    private val navigator: Navigator
):ViewModel() {
   fun init() {
        navigator.save(Screen.SPECIES_SCREEN)
    }
}