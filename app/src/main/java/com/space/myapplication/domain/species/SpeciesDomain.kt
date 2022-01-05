package com.space.myapplication.domain.species

class SpeciesDomain(
    private val id: Int,
    private val isBaby: Boolean,
    private val isLegendary: Boolean,
    private val isMythical: Boolean,
    private val name: String,
    private val baseHappiness: Int,
    private val captureRate: Int,
    private val color: String,
    private val evolvesFromName: String,
    private val evolvesFromUrl: String,
    private val formsSwitchable: Boolean,
    private val genderRate: Int,
    private val hasGenderDifferences: Boolean,
    private val hatchCounter: Int,
    private val order: Int,
) {
}