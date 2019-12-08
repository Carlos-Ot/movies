package com.ottoboni.movies.domain.model.parser

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season

object SeasonParser : Parser<SeasonResponse, Season> {
    override fun parse(remote: SeasonResponse): Season {
        return Season(
            airDate = remote.airDate,
            episodeCount = remote.episodeCount,
            id = remote.id,
            name = remote.name,
            overview = remote.overview,
            posterPath = remote.posterPath,
            seasonNumber = remote.seasonNumber,
            showId = 0,
            episodes = remote.episodes.map { EpisodeParser.parse(it) }
        )
    }
}