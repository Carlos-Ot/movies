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
            airDate = remote.airDate?.ifBlank { null },
            episodeCount = remote.episodeCount,
            id = remote.id,
            name = remote.name?.ifBlank { null },
            overview = remote.overview?.ifBlank { null },
            posterPath = remote.posterPath?.ifBlank { null },
            seasonNumber = remote.seasonNumber,
            showId = 0,
            episodes = remote.episodes?.ifEmpty { null }?.map(episodeFactory::make)
        )
}
