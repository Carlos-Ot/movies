package com.ottoboni.movies.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.datasource.ShowRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRetrofitDataSource
import com.ottoboni.movies.data.source.remote.error.NotFoundException
import com.ottoboni.movies.data.source.remote.error.UnauthorizedException
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.data.source.remote.model.enums.MediaType
import com.ottoboni.movies.data.source.remote.model.enums.TimeWindow
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.mocks.ErrorMocks.mockErrorResponse
import com.ottoboni.movies.mocks.ShowMocks.mockEmptyResults
import com.ottoboni.movies.mocks.ShowMocks.mockNotEmptyResults
import com.ottoboni.movies.mocks.ShowMocks.mockShow
import com.ottoboni.movies.mocks.ShowMocks.mockShowResponse
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
class ShowRetrofitDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var apiClient: TmdbApi

    @MockK
    private lateinit var showFactory: ModelFactory<ShowResponse, Show>

    @MockK
    private lateinit var exceptionFactory: ModelFactory<HttpException, Exception>

    private lateinit var dataSource: ShowRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        dataSource = ShowRetrofitDataSource(apiClient, showFactory, exceptionFactory)
    }

    @Test
    fun `test empty fetchPopular is turned to null`() = runBlocking {
        coEvery { apiClient.fetchPopularShowsAsync(any(), any()) } returns mockEmptyResults()

        val result = dataSource.fetchPopular(1, "")

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchPopularShowsAsync(any(), any()) }
        verify { showFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchPopular returns Show list`() = runBlocking {
        val resultsSize = 10
        coEvery {
            apiClient.fetchPopularShowsAsync(any(), any())
        } returns mockNotEmptyResults(resultsSize)
        every { showFactory.make(any()) } returns mockShow(1)

        val result = dataSource.fetchPopular(1, "")

        assertNotNull(result)
        assertTrue { result.size == resultsSize }
        coVerify(exactly = 1) { apiClient.fetchPopularShowsAsync(any(), any()) }
        verify(exactly = resultsSize) { showFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchPopular throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = mockErrorResponse(true)
        coEvery {
            apiClient.fetchPopularShowsAsync(any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchPopular(1, "")

        coVerify(exactly = 1) { apiClient.fetchPopularShowsAsync(any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchPopular throws NotFoundException`() = runBlockingTest {
        val errorResponse = mockErrorResponse()
        coEvery {
            apiClient.fetchPopularShowsAsync(any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchPopular(1, "")

        coVerify(exactly = 1) { apiClient.fetchPopularShowsAsync(any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test
    fun `test empty fetchTrending is turned to null`() = runBlocking {
        coEvery {
            apiClient.fetchTrendingShowsAsync(
                any(),
                any(),
                any()
            )
        } returns mockEmptyResults()

        val result = dataSource.fetchTrending(1, MediaType.TV, TimeWindow.WEEK)

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchTrendingShowsAsync(any(), any(), any()) }
        verify { showFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchTrending returns Show list`() = runBlocking {
        val resultsSize = 10
        coEvery {
            apiClient.fetchTrendingShowsAsync(any(), any(), any())
        } returns mockNotEmptyResults(resultsSize)
        every { showFactory.make(any()) } returns mockShow(1)

        val result = dataSource.fetchTrending(1, MediaType.TV, TimeWindow.WEEK)

        assertNotNull(result)
        assertTrue { result.size == resultsSize }
        coVerify(exactly = 1) { apiClient.fetchTrendingShowsAsync(any(), any(), any()) }
        verify(exactly = resultsSize) { showFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchTrending throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = mockErrorResponse(true)
        coEvery {
            apiClient.fetchTrendingShowsAsync(any(), any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchTrending(1, MediaType.TV, TimeWindow.WEEK)

        coVerify(exactly = 1) { apiClient.fetchTrendingShowsAsync(any(), any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchTrending throws NotFoundException`() = runBlockingTest {
        val errorResponse = mockErrorResponse()
        coEvery {
            apiClient.fetchTrendingShowsAsync(any(), any(), any())
        } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchTrending(1, MediaType.TV, TimeWindow.WEEK)

        coVerify(exactly = 1) { apiClient.fetchTrendingShowsAsync(any(), any(), any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test
    fun `test fetchBy returns null`() = runBlocking {
        coEvery { apiClient.fetchShowAsync(any()) } returns null

        val result = dataSource.fetchBy(1)

        assertNull(result)
        coVerify(exactly = 1) { apiClient.fetchShowAsync(any()) }
        verify { showFactory.make(any()) wasNot Called }
    }

    @Test
    fun `test fetchBy returns Show`() = runBlocking {
        coEvery { apiClient.fetchShowAsync(any()) } returns mockShowResponse(1)
        every { showFactory.make(any()) } returns mockShow(1)

        val result = dataSource.fetchBy(1)

        assertNotNull(result)
        coVerify(exactly = 1) { apiClient.fetchShowAsync(any()) }
        verify(exactly = 1) { showFactory.make(any()) }
    }

    @Test(expected = UnauthorizedException::class)
    fun `test fetchBy throws UnauthorizedException`() = runBlockingTest {
        val errorResponse = mockErrorResponse(true)
        coEvery { apiClient.fetchShowAsync(any()) } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns UnauthorizedException(errorResponse.message())

        dataSource.fetchBy(1)

        coVerify(exactly = 1) { apiClient.fetchShowAsync(any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }

    @Test(expected = NotFoundException::class)
    fun `test fetchBy throws NotFoundException`() = runBlockingTest {
        val errorResponse = mockErrorResponse(true)
        coEvery { apiClient.fetchShowAsync(any()) } throws HttpException(errorResponse)
        every {
            exceptionFactory.make(any())
        } returns NotFoundException(errorResponse.message())

        dataSource.fetchBy(1)

        coVerify(exactly = 1) { apiClient.fetchShowAsync(any()) }
        verify(exactly = 1) { exceptionFactory.make(any()) }
    }
}