package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.domain.model.Episode

object EpisodeMapper : Mapper<EpisodeEntity, Episode> {
    override fun toDomain(entity: EpisodeEntity): Episode {
        return Episode(
            id = entity.id,
            episodeNumber = entity.episodeNumber,
            airDate = entity.airDate,
            name = entity.name,
            overview = entity.overview,
            stillPath = entity.stillPath,
            voteAverage = entity.voteAverage,
            seasonId = entity.seasonId
        )
    }

    override fun fromDomain(domain: Episode): EpisodeEntity {
        return EpisodeEntity(
            id = domain.id,
            episodeNumber = domain.episodeNumber,
            airDate = domain.airDate,
            name = domain.name,
            overview = domain.overview,
            stillPath = domain.stillPath,
            voteAverage = domain.voteAverage,
            seasonId = domain.seasonId
        )
    }
}