package com.ottoboni.movies.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ottoboni.movies.data.remote.mocks.ApiResponseMocks
import com.ottoboni.movies.data.remote.mocks.GenreResponseMocks.mockEmptyResults
import com.ottoboni.movies.data.remote.mocks.GenreResponseMocks.mockGenre
import com.ottoboni.movies.data.remote.mocks.GenreResponseMocks.mockNotEmptyResults
import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.datasource.GenreRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.GenreRetrofitDataSource
import com.ottoboni.movies.data.source.remote.error.NotFoundException
import com.ottoboni.movies.data.source.remote.error.UnauthorizedException
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
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
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GenreRetrofitDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var apiClient: TmdbApi

    @MockK
    private lateinit var genreFactory: ModelFactory<GenreResponse, Genre>

    @MockK
    private lateinit var exceptionFactory: ModelFactory<HttpException, Exception>

    private lateinit var dataSource: GenreRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        dataSource = GenreRetrofitDataSource(apiClient, genreFactory, exceptionFactory)
    }

    @Test
    fun `test empty fetchGenres is turned to null`() = runBlocking {
        coEvery { apiClient.fetchGenresAsync() } returns mockEmptyResults()

        val result = dataSource.fetchGenres()

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchGenresAsync() }
        verify { genreFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchGenres returns Genre list`() = runBlocking {
        val resultSize = 10
        coEvery { apiClient.fetchGenresAsync() } returns mockNotEmptyResults(resultSize)
        every { genreFactory.make(any()) } returns mockGenre(1)

        val result = dataSource.fetchGenres()

        assertNotNull(result)
        assertTrue { result.size == resultSize }
        coVerify(exactly = 1) { apiClient.fetchGenresAsync() }
        verify(exactly = resultSize) { genreFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchGenres() throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse(true)
        coEvery { apiClient.fetchGenresAsync() } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchGenres()

        coVerify(exactly = 1) { apiClient.fetchGenresAsync() }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchGenres() throws NotFoundException`() = runBlockingTest {
        val errorResponse = ApiResponseMocks.mockErrorResponse()
        coEvery { apiClient.fetchGenresAsync() } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchGenres()

        coVerify(exactly = 1) { apiClient.fetchGenresAsync() }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }
}