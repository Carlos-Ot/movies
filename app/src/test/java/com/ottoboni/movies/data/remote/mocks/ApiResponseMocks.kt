package com.ottoboni.movies.data.remote.mocks

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show
import io.mockk.every
import io.mockk.mockk
import retrofit2.Response

object ApiResponseMocks {

    private const val HTTP_UNAUTHORIZED_CODE = 401
    private const val HTTP_UNAUTHORIZED_MESSAGE =
        "Invalid API key: You must be granted a valid key."
    private const val HTTP_NOT_FOUND_CODE = 404
    private const val HTTP_NOT_FOUND_MESSAGE = "The resource you requested could not be found."

    fun mockEmptyResults() = mockk<ApiResponse> {
        every { page } returns 1
        every { totalResults } returns 0
        every { totalPages } returns 1000
        every { results } returns emptyList()
    }

    fun mockNotEmptyResults(size: Int) = mockk<ApiResponse> {
        every { page } returns 1
        every { totalResults } returns size
        every { totalPages } returns 1000
        every { results } returns mockShowResponseList(size)
    }

    fun mockShow() = mockk<Show> {
        every { originalName } returns "originalName"
        every { genres } returns emptyList()
        every { genreIds } returns listOf(1, 2, 3)
        every { name } returns "name"
        every { popularity } returns 500F
        every { originCountry } returns listOf("br")
        every { voteCount } returns 400
        every { firstAirDate } returns "firstAirData"
        every { backdropUrl } returns "backdropUrl"
        every { originalLanguage } returns "originalLanguage"
        every { id } returns 1
        every { voteAverage } returns 8.7F
        every { overview } returns "overview"
        every { posterUrl } returns "posterUrl"
        every { seasons } returns emptyList()
    }

    fun mockErrorResponse(authorizationError: Boolean = false) = mockk<Response<ApiResponse>> {
        every { code() } returns if (authorizationError) HTTP_UNAUTHORIZED_CODE else HTTP_NOT_FOUND_CODE
        every { message() } returns if (authorizationError) HTTP_UNAUTHORIZED_MESSAGE else HTTP_NOT_FOUND_MESSAGE
    }

    fun mockShowResponse(mockId: Int) = mockk<ShowResponse> {
        every { originalName } returns "originalName$mockId"
        every { genreIds } returns listOf(1, 2, 3)
        every { name } returns "name$mockId"
        every { popularity } returns (500 + mockId).toFloat()
        every { originCountry } returns listOf("br")
        every { voteCount } returns (400 + mockId)
        every { firstAirDate } returns "firstAirData$mockId"
        every { backdropPath } returns "backdropPath$mockId"
        every { originalLanguage } returns "originalLanguage$mockId"
        every { id } returns mockId
        every { voteAverage } returns mockId.toFloat()
        every { overview } returns "overview$mockId"
        every { posterPath } returns "posterPath$mockId"
        every { genres } returns emptyList()
        every { seasons } returns emptyList()
    }

    private fun mockShowResponseList(size: Int) = mutableListOf<ShowResponse>()
        .apply { repeat(size) { this += mockShowResponse(it) } }
        .toList()
}