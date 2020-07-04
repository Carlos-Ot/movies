package com.ottoboni.movies.data.remote.mocks

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import io.mockk.every
import io.mockk.mockk

object EpisodeResponseMocks {
    fun mockEpisodeResponseList(size: Int) = mutableListOf<EpisodeResponse>()
        .apply { repeat(size) { this += mockEpisodeResponse(it)} }
        .toList()

    fun mockEpisodeResponse(mockId: Int) = mockk<EpisodeResponse> {
        every { episodeNumber } returns mockId
        every { airDate } returns "airDate$mockId"
        every { name } returns "name$mockId"
        every { overview } returns "overview$mockId"
        every { id } returns mockId
        every { stillPath } returns "stillPath$mockId"
        every { voteAverage } returns mockId.toFloat()
    }

    fun mockEpisodeList(size: Int) = mutableListOf<Episode>()
        .apply { repeat(size) { this += mockEpisode(it) } }
        .toList()

    fun mockEpisode(mockId: Int) = mockk<Episode> {
        every { episodeNumber } returns mockId
        every { airDate } returns "airDate$mockId"
        every { name } returns "name$mockId"
        every { overview } returns "overview$mockId"
        every { id } returns mockId
        every { stillPath } returns "stillPath$mockId"
        every { voteAverage } returns mockId.toFloat()
    }
}