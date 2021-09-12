package com.space.myapplication.data


import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.cache.UpcomingCacheDataSource
import com.space.myapplication.data.cache.UpcomingCacheMapper
import com.space.myapplication.data.cache.UpcomingEntity
import com.space.myapplication.data.cache.UpcomingListCacheMapper
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingDtoMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class UpcomingRepositoryTest : BaseUpcomingRepositoryTest() {

    val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = UpcomingRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            UpcomingListCloudMapper.Base(TestUpcomingDtoMapper()), // TODO use test mappers
            UpcomingListCacheMapper.Base(TestUpcomingCacheMapper())
        )

        val actual = repository.getUpcoming()
        val expected = UpcomingListData.Fail(unknownHostException)
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = UpcomingRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            UpcomingListCloudMapper.Base(TestUpcomingDtoMapper()), // TODO use test mappers
            UpcomingListCacheMapper.Base(TestUpcomingCacheMapper())
        )

        val actual = repository.getUpcoming()
        val expected = UpcomingListData.Success(
            listOf(
                Upcoming("Dragon 1", "wait"),
                Upcoming("Dragon 2", "launched"),
                Upcoming("Dragon 3", "prepare")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = UpcomingRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            UpcomingListCloudMapper.Base(TestUpcomingDtoMapper()),
            UpcomingListCacheMapper.Base(TestUpcomingCacheMapper())
        )

        val actual = repository.getUpcoming()
        val expected = UpcomingListData.Success(
            listOf(
                Upcoming("Dragon 10", "wait"),
                Upcoming("Dragon 20", "launched"),
                Upcoming("Dragon 30", "prepare")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = UpcomingRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            UpcomingListCloudMapper.Base(UpcomingDtoMapper.Base()),
            UpcomingListCacheMapper.Base(TestUpcomingCacheMapper())
        )

        val actual = repository.getUpcoming()
        val expected = UpcomingListData.Success(
            listOf(
                Upcoming("Dragon 10", "wait"),
                Upcoming("Dragon 20", "launched"),
                Upcoming("Dragon 30", "prepare")
            )
        )
        assertEquals(expected, actual)
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

    private inner class TestCacheDataSource(
        private val returnSuccess: Boolean,
    ) : UpcomingCacheDataSource {

        override fun getUpcomingList(): List<UpcomingEntity> {
            return if (returnSuccess) {
                listOf(
                    UpcomingEntity().apply {
                        id = 1
                        capsule_id = "Dragon 10"
                        status = "wait"
                    },
                    UpcomingEntity().apply {
                        id = 2
                        capsule_id = "Dragon 20"
                        status = "launched"
                    },
                    UpcomingEntity().apply {
                        id = 3
                        capsule_id = "Dragon 30"
                        status = "prepare"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun saveUpcomingList(upcomingList: List<Upcoming>) {

        }
    }
}

