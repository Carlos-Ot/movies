package com.ottoboni.movies.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ottoboni.movies.data.remote.mocks.ApiResponseMocks
import com.ottoboni.movies.data.remote.mocks.SeasonResponseMocks
import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.datasource.SeasonRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.SeasonRetrofitDataSource
import com.ottoboni.movies.data.source.remote.error.NotFoundException
import com.ottoboni.movies.data.source.remote.error.UnauthorizedException
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
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
class SeasonRetrofitDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var apiClient: TmdbApi

    @MockK
    private lateinit var seasonFactory: ModelFactory<SeasonResponse, Season>

    @MockK
    private lateinit var exceptionFactory: ModelFactory<HttpException, Exception>

    private lateinit var dataSource: SeasonRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        dataSource = SeasonRetrofitDataSource(apiClient, seasonFactory, exceptionFactory)
    }

    @Test
    fun `test fetchSeason return null`() = runBlocking {
        coEvery { apiClient.fetchSeasonAsync(any(), any()) } returns null

        val result = dataSource.fetchSeason(1, 1)

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchSeasonAsync(any(), any()) }
        verify { seasonFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchSeason return Season`() = runBlocking {
        coEvery {
            apiClient.fetchSeasonAsync(any(), any())
        } returns SeasonResponseMocks.mockSeasonResponse(1)
        every { seasonFactory.make(any()) } returns SeasonResponseMocks.mockSeason(1)

        val result = dataSource.fetchSeason(1, 1)

        assertNotNull(result)
        coVerify(exactly = 1) { apiClient.fetchSeasonAsync(any(), any()) }
        verify(exactly = 1) { seasonFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchSeason throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse(true)
        coEvery {
            apiClient.fetchSeasonAsync(any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchSeason(1, 1)

        coVerify(exactly = 1) { apiClient.fetchSeasonAsync(any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchSeason throws NotFoundException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse(true)
        coEvery {
            apiClient.fetchSeasonAsync(any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchSeason(1, 1)

        coVerify(exactly = 1) { apiClient.fetchSeasonAsync(any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }
}
