package com.space.myapplication.data


import com.space.myapplication.data.cache.PokemonCacheDataSource
import com.space.myapplication.data.cache.PokemonEntity
import com.space.myapplication.data.cache.PokemonsCacheMapper
import com.space.myapplication.data.net.PokemonDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class PokemonRepositoryTest : BasePokemonRepositoryTest() {

    val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = PokemonRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper())
        )

        val actual = repository.getPokemon()
        val expected = PokemonsData.Fail(unknownHostException)
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = PokemonRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper())
        )

        val actual = repository.getPokemon()
        val expected = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 1", "wait"),
                PokemonData("Dragon 2", "launched"),
                PokemonData("Dragon 3", "prepare")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = PokemonRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper())
        )

        val actual = repository.getPokemon()
        val expected = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 10", "wait"),
                PokemonData("Dragon 20", "launched"),
                PokemonData("Dragon 30", "prepare")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = PokemonRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper())
        )

        val actual = repository.getPokemon()
        val expected = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 10", "wait"),
                PokemonData("Dragon 20", "launched"),
                PokemonData("Dragon 30", "prepare")
            )
        )
        assertEquals(expected, actual)
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

    private inner class TestCacheDataSource(
        private val returnSuccess: Boolean,
    ) : PokemonCacheDataSource {

        override fun getPokemonList(): List<PokemonEntity> {
            return if (returnSuccess) {
                listOf(
                    PokemonEntity().apply {
                        id = 1
                        name = "Dragon 10"
                        url = "wait"
                    },
                    PokemonEntity().apply {
                        id = 2
                        name = "Dragon 20"
                        url = "launched"
                    },
                    PokemonEntity().apply {
                        id = 3
                        name = "Dragon 30"
                        url = "prepare"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun savePokemonList(pokemons: List<PokemonData>) {

        }
    }
}

