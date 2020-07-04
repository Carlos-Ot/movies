package com.ottoboni.movies.data.remote.mocks

import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
import io.mockk.every
import io.mockk.mockk

object GenreResponseMocks {
    fun mockEmptyResults() = mockk<GenreApiResponse> {
        every { genres } returns emptyList()
    }

    fun mockNotEmptyResults(size: Int) = mockk<GenreApiResponse> {
        every { genres } returns mockGenreResponseList(size)
    }

    fun mockGenre(mockId: Int) = mockk<Genre> {
        every { id } returns mockId
        every { name } returns "name$mockId"
    }

    private fun mockGenreResponseList(size: Int) = mutableListOf<GenreResponse>()
        .apply { repeat(size) { this += mockGenreResponse(it) } }
        .toList()

    private fun mockGenreList(size: Int) = mutableListOf<Genre>()
        .apply { repeat(size) { this += mockGenre(it) } }
        .toList()

    private fun mockGenreResponse(mockId: Int) = mockk<GenreResponse> {
        every { id } returns mockId
        every { name } returns "name$mockId"
    }
}