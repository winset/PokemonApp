package com.space.myapplication.data


abstract class BasePokemonRepositoryTest {

    protected inner class TestToPokemonMapper : ToPokemonMapper {
        override fun map(name: String, url: String) = PokemonData(name, url)

    }

    protected inner class TestPokemonCacheMapper : ToPokemonMapper {
        override fun map(name: String, url: String) =
            PokemonData(name, url)
    }
}