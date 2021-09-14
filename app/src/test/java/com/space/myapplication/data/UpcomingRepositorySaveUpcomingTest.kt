package com.space.myapplication.data

import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.cache.UpcomingCacheDataSource
import com.space.myapplication.data.cache.UpcomingEntity
import com.space.myapplication.data.cache.UpcomingListCacheMapper
import com.space.myapplication.data.net.UpcomingDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class UpcomingRepositorySaveUpcomingTest : BaseUpcomingRepositoryTest() {
    val unknownHostException = UnknownHostException()

    @Test
    fun test_save_upcomings() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource()
        val repository = UpcomingRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            UpcomingListCloudMapper.Base(TestUpcomingDtoMapper()),
            UpcomingListCacheMapper.Base(TestUpcomingCacheMapper())
        )

        val actualCloud = repository.getUpcoming()
        val expectedCloud = UpcomingListData.Success(
            listOf(
                Upcoming("Dragon 1", "wait"),
                Upcoming("Dragon 2", "launched"),
                Upcoming("Dragon 3", "prepare")
            )
        )
        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.getUpcoming()
        val expectedCache = UpcomingListData.Success(
            listOf(
                Upcoming("Dragon 1 db", "wait"),
                Upcoming("Dragon 2 db", "launched"),
                Upcoming("Dragon 3 db", "prepare")
            )
        )
        assertEquals(expectedCache, actualCache)
    }

    private inner class TestCloudDataSource(
        private val returnSuccess: Boolean
    ) : UpcomingCloudDataSource {
        override suspend fun getUpcoming(): List<UpcomingDto> {
            return if (returnSuccess) {
                listOf(
                    UpcomingDto("Dragon 1", "wait"),
                    UpcomingDto("Dragon 2", "launched"),
                    UpcomingDto("Dragon 3", "prepare")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestCacheDataSource() : UpcomingCacheDataSource {

        private val list = mutableListOf<UpcomingEntity>()

        override fun getUpcomingList(): List<UpcomingEntity> = list

        override fun saveUpcomingList(upcomingList: List<Upcoming>) {
            var autoId = -1
            upcomingList.map { upcoming ->
                list.add(UpcomingEntity().apply {
                    id = autoId++
                    capsule_id = "${upcoming.capsule_id} db"
                    status = upcoming.status
                })
            }
        }
    }


}