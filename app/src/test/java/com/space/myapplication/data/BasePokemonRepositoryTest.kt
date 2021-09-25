package com.space.myapplication.data


abstract class BasePokemonRepositoryTest {

    protected inner class TestToPokemonMapper : ToPokemonMapper {
        override fun map(capsule_id: String, status: String) = PokemonData(capsule_id, status)

    }

    protected inner class TestPokemonCacheMapper : ToPokemonMapper {
        override fun map(capsule_id: String, status: String) =
            PokemonData(capsule_id, status)
    }
}