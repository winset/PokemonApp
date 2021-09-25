package com.space.myapplication.data

class PokemonRepositorySavePokemonTest : BasePokemonRepositoryTest() {
/*    val unknownHostException = UnknownHostException()

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

        val actualCloud = repository.getUpcoming()
        val expectedCloud = PokemonsData.Success(
            listOf(
                PokemonData("Dragon 1", "wait"),
                PokemonData("Dragon 2", "launched"),
                PokemonData("Dragon 3", "prepare")
            )
        )
        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.getUpcoming()
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
        override suspend fun getUpcoming(): List<PokemonDto> {
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

        override fun getUpcomingList(): List<PokemonEntity> = list

        override fun saveUpcomingList(upcomingList: List<PokemonData>) {
            var autoId = -1
            upcomingList.map { upcoming ->
                list.add(PokemonEntity().apply {
                    id = autoId++
                    name = "${upcoming.capsule_id} db"
                    url = upcoming.status
                })
            }
        }
    }*/
}