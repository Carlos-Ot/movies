package com.ottoboni.movies.domain.model.factory

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.Season
import javax.inject.Inject

class SeasonFactory @Inject constructor(
    private val episodeFactory: ModelFactory<EpisodeResponse, Episode>
) : ModelFactory<SeasonResponse, Season> {
    override fun make(remote: SeasonResponse) =
        Season(
            airDate = remote.airDate,
            episodeCount = remote.episodeCount,
            id = remote.id,
            name = remote.name,
            overview = remote.overview,
            posterPath = remote.posterPath,
            seasonNumber = remote.seasonNumber,
            showId = 0,
            episodes = remote.episodes.map(episodeFactory::make)
        )
}
