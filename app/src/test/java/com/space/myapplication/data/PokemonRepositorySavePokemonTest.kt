package com.space.myapplication.data

import com.space.myapplication.data.cache.PokemonCacheDataSource
import com.space.myapplication.data.cache.PokemonDataToDbMapper
import com.space.myapplication.data.cache.PokemonEntity
import com.space.myapplication.data.cache.PokemonsCacheMapper
import com.space.myapplication.data.net.PokemonDto
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

/**
 * Test for [PokemonRepository]
 *
 * **/
class PokemonRepositorySavePokemonTest : BasePokemonRepositoryTest() {
   val unknownHostException = UnknownHostException()

    @Test
    fun test_save_upcomings() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource()
        val repository = PokemonRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper())
        )

        val actualCloud = repository.getPokemon()
        val expectedCloud = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 1", "wait"),
                PokemonData("Dragon 2", "launched"),
                PokemonData("Dragon 3", "prepare")
            )
        )
        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.getPokemon()
        val expectedCache = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 1 db", "wait"),
                PokemonData("Dragon 2 db", "launched"),
                PokemonData("Dragon 3 db", "prepare")
            )
        )
        assertEquals(expectedCache, actualCache)
    }

    private inner class TestCloudDataSource(
        private val returnSuccess: Boolean
    ) : PokemonCloudDataSource {
        override suspend fun getPokemon(): List<PokemonDto> {
            return if (returnSuccess) {
                listOf(
                    PokemonDto("Dragon 1", "wait"),
                    PokemonDto("Dragon 2", "launched"),
                    PokemonDto("Dragon 3", "prepare")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestCacheDataSource() : PokemonCacheDataSource {

        private val list = mutableListOf<PokemonEntity>()

        override fun getPokemonList(): List<PokemonEntity> = list

        override fun savePokemonList(pokemonsData: List<PokemonData>) {
            var autoId = -1
            pokemonsData.map { pokemon ->
                val mapper = PokemonDataToDbMapper.Base()
                list.add(PokemonEntity().apply {
                    id = autoId++
             //       name = "${pokemon.mapTo(mapper)} db" //TODO fix it
               //     url = pokemon.status
                })
            }
        }
    }
}