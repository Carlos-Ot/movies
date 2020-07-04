package com.ottoboni.movies.data.remote.mocks

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
import io.mockk.every
import io.mockk.mockk

object SeasonResponseMocks {
    fun mockSeasonResponse(mockId: Int) = mockk<SeasonResponse> {
        every { airDate } returns "airDate$mockId"
        every { episodeCount } returns 10
        every { id } returns mockId
        every { name } returns "name$mockId"
        every { overview } returns "overview$mockId"
        every { posterPath } returns "posterPath$mockId"
        every { seasonNumber } returns 1
        every { episodes } returns EpisodeResponseMocks.mockEpisodeResponseList(10)
    }

    fun mockSeason(mockId: Int) = mockk<Season> {
        every { airDate } returns "airDate$mockId"
        every { episodeCount } returns 10
        every { id } returns mockId
        every { name } returns "name$mockId"
        every { overview } returns "overview$mockId"
        every { posterPath } returns "posterPath$mockId"
        every { seasonNumber } returns 1
        every { episodes } returns EpisodeResponseMocks.mockEpisodeList(10)
    }
}