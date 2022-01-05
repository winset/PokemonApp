package com.space.myapplication.presentation

import com.space.myapplication.R
import com.space.myapplication.domain.pokemons.ErrorType
import com.space.myapplication.domain.pokemons.PokemonDomainToUiMapper
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

/**
 * Test for [BasePokemonsDomainToUiMapper]
 *
 * **/
class BasePokemonsDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper =
            BasePokemonsDomainToUiMapper(resourceProvider, object : PokemonDomainToUiMapper {
                override fun map(name: String, url: String): PokemonUi {
                    throw IllegalStateException()//not used here
                }
            })

        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = PokemonsUi.Base(listOf(PokemonUi.Fail("no_connection")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = PokemonsUi.Base(listOf(PokemonUi.Fail("server_not_available")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = PokemonsUi.Base(listOf(PokemonUi.Fail("something_go_wrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection -> "no_connection"
            R.string.server_not_available -> "server_not_available"
            else -> "something_go_wrong"
        }
    }
}