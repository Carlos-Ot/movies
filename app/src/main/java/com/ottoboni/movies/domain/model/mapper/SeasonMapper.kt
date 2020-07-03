package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.domain.model.Season
import javax.inject.Inject

class SeasonMapper @Inject constructor() : Mapper<SeasonEntity, Season> {
    override fun toDomain(entity: SeasonEntity) =
        Season(
            airDate = entity.airDate,
            episodeCount = entity.episodeCount,
            id = entity.id,
            name = entity.name,
            overview = entity.overview,
            posterPath = entity.posterPath,
            seasonNumber = entity.seasonNumber,
            showId = entity.showId,
            episodes = emptyList()
        )

    override fun fromDomain(domain: Season) =
        SeasonEntity(
            airDate = domain.airDate,
            episodeCount = domain.episodeCount,
            id = domain.id,
            name = domain.name,
            overview = domain.overview,
            posterPath = domain.posterPath,
            seasonNumber = domain.seasonNumber,
            showId = domain.showId
        )
}
