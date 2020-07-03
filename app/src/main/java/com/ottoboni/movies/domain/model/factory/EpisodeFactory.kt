package com.ottoboni.movies.domain.model.factory

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import javax.inject.Inject

class EpisodeFactory @Inject constructor() : ModelFactory<EpisodeResponse, Episode> {
    override fun make(remote: EpisodeResponse) =
        Episode(
            episodeNumber = remote.episodeNumber,
            airDate = remote.airDate?.ifBlank { null },
            name = remote.name?.ifBlank { null },
            overview = remote.overview?.ifBlank { null },
            id = remote.id,
            stillPath = remote.stillPath?.ifBlank { null },
            voteAverage = remote.voteAverage,
            seasonId = 0
        )
}
