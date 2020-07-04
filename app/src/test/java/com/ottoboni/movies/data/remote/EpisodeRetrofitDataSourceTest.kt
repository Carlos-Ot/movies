package com.ottoboni.movies.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ottoboni.movies.data.remote.mocks.ApiResponseMocks
import com.ottoboni.movies.data.remote.mocks.EpisodeResponseMocks.mockEpisode
import com.ottoboni.movies.data.remote.mocks.EpisodeResponseMocks.mockEpisodeResponse
import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.datasource.EpisodeRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.EpisodeRetrofitDataSource
import com.ottoboni.movies.data.source.remote.error.NotFoundException
import com.ottoboni.movies.data.source.remote.error.UnauthorizedException
import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.factory.ModelFactory
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
class EpisodeRetrofitDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var apiClient: TmdbApi

    @MockK
    private lateinit var episodeFactory: ModelFactory<EpisodeResponse, Episode>

    @MockK
    private lateinit var exceptionFactory: ModelFactory<HttpException, Exception>

    private lateinit var dataSource: EpisodeRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        dataSource = EpisodeRetrofitDataSource(apiClient, episodeFactory, exceptionFactory)
    }

    @Test
    fun `test fetchEpisode return null`() = runBlocking {
        coEvery { apiClient.fetchEpisodeAsync(any(), any(), any()) } returns null

        val result = dataSource.fetchEpisode(1, 1, 1)

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchEpisodeAsync(any(), any(), any()) }
        verify { episodeFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchEpisode return Episode`() = runBlocking {
        val episodeId = 1
        coEvery {
            apiClient.fetchEpisodeAsync(any(), any(), any())
        } returns mockEpisodeResponse(episodeId)
        every { episodeFactory.make(any()) } returns mockEpisode(episodeId)

        val result = dataSource.fetchEpisode(1, 1, episodeId)

        assertNotNull(result)
        coVerify(exactly = 1) { apiClient.fetchEpisodeAsync(any(), any(), any()) }
        verify(exactly = 1) { episodeFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchEpisode throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse(true)
        coEvery {
            apiClient.fetchEpisodeAsync(any(), any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchEpisode(1, 1, 1)

        coVerify(exactly = 1) { apiClient.fetchEpisodeAsync(any(), any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchEpisode throws NotFoundException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse(true)
        coEvery {
            apiClient.fetchEpisodeAsync(any(), any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchEpisode(1, 1, 1)

        coVerify(exactly = 1) { apiClient.fetchEpisodeAsync(any(), any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }
}