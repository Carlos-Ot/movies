package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.SeasonWithEpisodes
import com.ottoboni.movies.domain.model.Season

object SeasonWithEpisodesMapper {
    fun toDomain(entity: SeasonWithEpisodes): Season {
        return Season(
            airDate = entity.season.airDate,
            episodeCount = entity.season.episodeCount,
            id = entity.season.id,
            name = entity.season.name,
            overview = entity.season.overview,
            posterPath = entity.season.posterPath,
            seasonNumber = entity.season.seasonNumber,
            showId = entity.season.showId,
            episodes = entity.episodes.map { EpisodeMapper.toDomain(it) }
        )
    }
}