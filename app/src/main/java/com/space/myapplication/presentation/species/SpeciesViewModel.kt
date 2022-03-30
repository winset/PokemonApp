package com.space.myapplication.presentation.species

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpeciesViewModel @Inject constructor(
    private val interactor: SpeciesInteractor,
    private val uiMapper: SpeciesDomainToUiMapper<SpeciesUi>,
    private val communication: SpeciesCommunication
) : ViewModel() {

    fun getInfo(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val uiInfo = interactor.getSpecies(name).map(uiMapper)
            withContext(Dispatchers.Main) {
                communication.map(uiInfo)
            }
        }
    }
}