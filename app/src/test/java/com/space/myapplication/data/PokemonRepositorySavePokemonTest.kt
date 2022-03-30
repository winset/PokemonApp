package com.space.myapplication.data

import com.space.myapplication.core.DbWrapper
import com.space.myapplication.data.pokemons.*
import com.space.myapplication.data.pokemons.cache.PokemonCacheDataSource
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cache.PokemonEntity
import com.space.myapplication.data.pokemons.cache.PokemonsCacheMapper
import com.space.myapplication.data.pokemons.cloud.PokemonCloudDataSource
import com.space.myapplication.data.pokemons.cloud.PokemonDto
import com.space.myapplication.data.pokemons.cloud.PokemonsCloudMapper
import com.space.myapplication.domain.pokemons.PokemonDomain
import io.realm.Realm
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
        val repository = BasePokemonRepository(
            testCloudDataSource,
            testCacheDataSource,
            PokemonsCloudMapper.Base(TestToPokemonMapper()),
            PokemonsCacheMapper.Base(TestPokemonCacheMapper()),
            mapper
        )
        val page = 0
        val actualCloud = repository.getPokemon(page)
        val expectedCloud = PokemonsData.Success(
            listOf(
                PokemonData.Base(
                    "Dragon 1",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"
                ),
                PokemonData.Base(
                    "Dragon 2",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"
                ),
                PokemonData.Base(
                    "Dragon 3",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png"
                )
            )
        )
        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.getPokemon(page)
        val expectedCache = PokemonsData.Success(
            listOf(
                PokemonData.Base(
                    "Dragon 1 db",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10094.png"
                ),
                PokemonData.Base(
                    "Dragon 2 db",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10095.png"
                ),
                PokemonData.Base(
                    "Dragon 3 db",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10096.png"
                )
            )
        )
        assertEquals(expectedCache, actualCache)
    }

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

    private val mapper = BasePokemonsDataToDomainMapper(object : PokemonDataToDomainMapper<PokemonDomain> {
        override fun map(name: String, url: String) = PokemonDomain(name, url)
    })

    private inner class TestCacheDataSource() : PokemonCacheDataSource {

        private val list = mutableListOf<PokemonEntity>()

        override fun getPokemonList(page: Int): List<PokemonEntity> = list

        override fun savePokemonList(pokemonsData: List<PokemonData>, page: Int) {
            pokemonsData.map { pokemon ->
                val mapper = PokemonDataToDbMapper.Base()
                list.add(PokemonEntity().apply {
                    name = "${
                        ""
                        /*pokemon.mapTo(
                            mapper,
                            Realm.getDefaultInstance(),
                            page
                        ).name*/
                    } db" //TODO fix it
                    url = pokemon.map(mapper, PokemonDbWrapper(Realm.getDefaultInstance()), page).url
                })
            }
        }

        private inner class PokemonDbWrapper(realm: Realm) :
            DbWrapper.Base<PokemonEntity>(realm) {
            override fun dbClass(): Class<PokemonEntity> = PokemonEntity::class.java
        }
    }
}