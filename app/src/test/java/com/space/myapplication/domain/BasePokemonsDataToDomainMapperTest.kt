package com.space.myapplication.domain

import com.space.myapplication.core.ErrorType
import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.PokemonDataToDomainMapper
import com.space.myapplication.data.pokemons.BasePokemonsDataToDomainMapper
import com.space.myapplication.domain.pokemons.PokemonDomain
import com.space.myapplication.domain.pokemons.PokemonsDomain
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

/**
 * Test for [BasePokemonsDataToDomainMapper]
 *
 * **/
class BasePokemonsDataToDomainMapperTest {

    private val mapper = BasePokemonsDataToDomainMapper(object : PokemonDataToDomainMapper<PokemonDomain> {
        override fun map(name: String, url: String) = PokemonDomain(name, url)
    })

    @Test
    fun test_success() {
        val actual = mapper.map(
            listOf(
                PokemonData.Base("1", "url1"),
                PokemonData.Base("2", "url2"),
                PokemonData.Base("3", "url3")
            )
        )

        val data = listOf(
            PokemonDomain("1", "url1"),
            PokemonDomain("2", "url2"),
            PokemonDomain("3", "url3")
        )

        val expected = PokemonsDomain.Success(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var actual = mapper.map(UnknownHostException())
        var expected = PokemonsDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalStateException())
        expected = PokemonsDomain.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }
}