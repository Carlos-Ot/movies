package com.ottoboni.movies.domain.model.parser

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode

object EpisodeParser : Parser<EpisodeResponse, Episode> {
    override fun parse(remote: EpisodeResponse): Episode {
        return Episode(
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
}