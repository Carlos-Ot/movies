package com.ottoboni.movies.domain.model.factory

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import javax.inject.Inject

class EpisodeFactory @Inject constructor() : ModelFactory<EpisodeResponse, Episode> {
    override fun make(remote: EpisodeResponse) =
        Episode(
            episodeNumber = remote.episodeNumber,
            airDate = remote.airDate,
            name = remote.name,
            overview = remote.overview,
            id = remote.id,
            stillPath = remote.stillPath,
            voteAverage = remote.voteAverage,
            seasonId = 0
        )
}