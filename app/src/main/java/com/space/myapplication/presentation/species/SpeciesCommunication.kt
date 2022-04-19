package com.space.myapplication.presentation.species

import com.space.myapplication.core.Communication

interface SpeciesCommunication : Communication<SpeciesUi> {
    class Base : Communication.Base<SpeciesUi>(), SpeciesCommunication
}