package com.ottoboni.movies.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ottoboni.movies.data.source.local.cache.ListCache
import com.ottoboni.movies.data.source.local.datasource.ShowLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRemoteDataSource
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.repository.ShowRepository.Companion.PAGE_SIZE
import com.ottoboni.movies.mocks.ShowMocks.mockShowList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ShowRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var localDataSource: ShowLocalDataSource

    @MockK
    private lateinit var remoteDataSource: ShowRemoteDataSource

    @MockK(relaxUnitFun = true)
    private lateinit var popularCache: ListCache<Show>

    @MockK(relaxUnitFun = true)
    lateinit var trendingCache: ListCache<Show>

    private lateinit var repository: IShowRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = ShowRepository(localDataSource, remoteDataSource, popularCache, trendingCache)
    }

    @Test
    fun `test fetchPopular is taking from cache`() = runBlocking {
        every { popularCache.items } returns mockShowList(PAGE_SIZE)
        coEvery { remoteDataSource.fetchPopular(any(), any()) } returns mockShowList(PAGE_SIZE)

        val result = repository.fetchPopular(1, "")

        assertNotNull(result)
        assertEquals(PAGE_SIZE, result.size)
        verify(exactly = 1) { popularCache.items }
        coVerify(exactly = 0) { remoteDataSource.fetchPopular(any(), any()) }
    }

    @Test
    fun `test fetchPopular take from remoteDataSource when cache empty`() = runBlocking {
        every { popularCache.items } returns emptyList()
        coEvery { remoteDataSource.fetchPopular(any(), any()) } returns mockShowList(PAGE_SIZE)

        val result = repository.fetchPopular(1, "")

        assertNotNull(result)
        assertEquals(PAGE_SIZE, result.size)
        verify(exactly = 1) { popularCache.items }
        coVerify(exactly = 1) { remoteDataSource.fetchPopular(any(), any()) }
    }

    @Test
    fun `test fetchPopular take from remoteDataSource when cache items is less than PAGE_SIZE`() =
        runBlocking {
            every { popularCache.items } returns mockShowList(10)
            coEvery { remoteDataSource.fetchPopular(any(), any()) } returns mockShowList(PAGE_SIZE)

            val result = repository.fetchPopular(1, "")

            assertNotNull(result)
            assertEquals(PAGE_SIZE, result.size)
            verify(exactly = 1) { popularCache.items }
            coVerify(exactly = 1) { remoteDataSource.fetchPopular(any(), any()) }
        }

    @Test
    fun `test fetchTrending is taking from cache`() = runBlocking {
        every { trendingCache.items } returns mockShowList(PAGE_SIZE)
        coEvery { remoteDataSource.fetchTrending(any(), any(), any()) } returns mockShowList(
            PAGE_SIZE
        )

        val result = repository.fetchTrending(1)

        assertNotNull(result)
        assertEquals(PAGE_SIZE, result.size)
        verify(exactly = 1) { trendingCache.items }
        coVerify(exactly = 0) { remoteDataSource.fetchTrending(any(), any(), any()) }
    }

    @Test
    fun `test fetchTrending take from remoteDataSource when cache empty`() = runBlocking {
        every { trendingCache.items } returns emptyList()
        coEvery { remoteDataSource.fetchTrending(any(), any(), any()) } returns mockShowList(
            PAGE_SIZE
        )

        val result = repository.fetchTrending(1)

        assertNotNull(result)
        assertEquals(PAGE_SIZE, result.size)
        verify(exactly = 1) { trendingCache.items }
        coVerify(exactly = 1) { remoteDataSource.fetchTrending(any(), any(), any()) }
    }

    @Test
    fun `test fetchTrending take from remoteDataSource when cache items is less than PAGE_SIZE`() =
        runBlocking {
            every { trendingCache.items } returns mockShowList(10)
            coEvery { remoteDataSource.fetchTrending(any(), any(), any()) } returns mockShowList(PAGE_SIZE)

            val result = repository.fetchTrending(1)

            assertNotNull(result)
            assertEquals(PAGE_SIZE, result.size)
            verify(exactly = 1) { trendingCache.items }
            coVerify(exactly = 1) { remoteDataSource.fetchTrending(any(), any(), any()) }
        }
}