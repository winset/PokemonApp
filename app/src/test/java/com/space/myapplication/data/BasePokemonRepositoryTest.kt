package com.space.myapplication.data

import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.ToPokemonMapper


abstract class BasePokemonRepositoryTest {

    protected inner class TestToPokemonMapper : ToPokemonMapper {
        override fun map(name: String, url: String) = PokemonData(name, url)
    }

    protected inner class TestPokemonCacheMapper : ToPokemonMapper {
        override fun map(name: String, url: String) = PokemonData(name, url)
    }
}