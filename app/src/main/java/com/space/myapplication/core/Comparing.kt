package com.space.myapplication.core

interface Comparing<T:Comparing<T>> {
    fun same(pokemonUi: T) = false
    fun sameContent(pokemonUi: T) = false
}