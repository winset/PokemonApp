package com.space.myapplication.data


import com.space.myapplication.data.pokemons.*
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonEntity
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.cloud.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.cloud.PokemonDto
import com.space.myapplication.data.pokemons.cloud.PokemonsCloudMapper
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.domain.pokemons.PokemonsDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

/**
 * Test for [PokemonRepository]
 * **/
class PokemonRepositoryTest : BasePokemonRepositoryTest() {

    val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = BasePokemonRepository<PokemonsDomain>(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper()),
            mapper
        )
        val page = 0
        val actual = repository.getPokemon(page)
        val expected = PokemonsData.Fail(unknownHostException)
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = BasePokemonRepository<PokemonsDomain>(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper()),
            mapper
        )
        val page = 0
        val actual = repository.getPokemon(page)
        val expected = PokemonsDomain.Success(
            listOf(
                PokemonDomain("Dragon 1", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"),
                PokemonDomain("Dragon 2", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"),
                PokemonDomain("Dragon 3", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = BasePokemonRepository<PokemonsDomain>(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper()),
            mapper
        )
        val page = 0
        val actual = repository.getPokemon(page)
        val expected = PokemonsDomain.Success(
            listOf(
                PokemonDomain("Dragon 10", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"),
                PokemonDomain("Dragon 20", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"),
                PokemonDomain("Dragon 30", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = BasePokemonRepository<PokemonsDomain>(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper()),
            mapper
        )
        val page = 0
        val actual = repository.getPokemon(page)
        val expected = PokemonsDomain.Success(
            listOf(
                PokemonDomain("Dragon 10", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"),
                PokemonDomain("Dragon 20", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"),
                PokemonDomain("Dragon 30", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png")
            )
        )
        assertEquals(expected, actual)
    }

    private val mapper = BasePokemonsDataToDomainMapper(object : PokemonDataToDomainMapper<PokemonDomain> {
        override fun map(name: String, url: String) = PokemonDomain(name, url)
    })

    private inner class TestCloudDataSource(
        private val returnSuccess: Boolean
    ) : PokemonCloudDataSource {
        override suspend fun getPokemon(page: Int): List<PokemonDto> {
            return if (returnSuccess) {
                listOf(
                    PokemonDto("Dragon 1", "https://pokeapi.co/api/v2/pokemon/10094/"),
                    PokemonDto("Dragon 2", "https://pokeapi.co/api/v2/pokemon/10095/"),
                    PokemonDto("Dragon 3", "https://pokeapi.co/api/v2/pokemon/10096/")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestCacheDataSource(
        private val returnSuccess: Boolean,
    ) : PokemonCacheDataSource {

        override fun getPokemonList(page: Int): List<PokemonEntity> {
            return if (returnSuccess) {
                listOf(
                    PokemonEntity().apply {
                        name = "Dragon 10"
                        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"
                    },
                    PokemonEntity().apply {
                        name = "Dragon 20"
                        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"
                    },
                    PokemonEntity().apply {
                        name = "Dragon 30"
                        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun savePokemonList(pokemonsData: List<PokemonData>, page: Int) {
        }
    }
}