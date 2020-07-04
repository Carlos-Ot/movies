package com.ottoboni.movies.mocks

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show
import io.mockk.every
import io.mockk.mockk

object ShowMocks {
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

    fun mockShow(mockId: Int) = mockk<Show> {
        every { originalName } returns "originalName$mockId"
        every { genres } returns GenreMocks.mockGenreList(3)
        every { genreIds } returns listOf(1, 2, 3)
        every { name } returns "name$mockId"
        every { popularity } returns 500F
        every { originCountry } returns listOf("br")
        every { voteCount } returns 400
        every { firstAirDate } returns "firstAirData$mockId"
        every { backdropUrl } returns "backdropUrl$mockId"
        every { originalLanguage } returns "originalLanguage$mockId"
        every { id } returns mockId
        every { voteAverage } returns 8.7F
        every { overview } returns "overview$mockId"
        every { posterUrl } returns "posterUrl$mockId"
        every { seasons } returns SeasonMocks.mockSeasonList(5)
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

    fun mockShowList(size: Int) = mutableListOf<Show>()
        .apply { repeat(size) { this += mockShow(it) } }
        .toList()

    private fun mockShowResponseList(size: Int) = mutableListOf<ShowResponse>()
        .apply { repeat(size) { this += mockShowResponse(it) } }
        .toList()
}