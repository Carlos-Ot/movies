package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.ShowEntity
import com.ottoboni.movies.domain.model.Show

object ShowMapper : Mapper<ShowEntity, Show> {
    override fun toDomain(entity: ShowEntity): Show {
        return Show(
            originalName = entity.originalName,
            genres = emptyList(),
            genreIds = entity.genreIds,
            name = entity.name,
            popularity = entity.popularity,
            originCountry = entity.originCountry,
            voteCount = entity.voteCount,
            firstAirDate = entity.firstAirDate,
            backdropPath = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            id = entity.id,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            posterPath = entity.posterPath,
            seasons = emptyList()
        )
    }

    override fun fromDomain(domain: Show): ShowEntity {
        return ShowEntity(
            originalName = domain.originalName,
            genreIds = domain.genreIds,
            name = domain.name,
            popularity = domain.popularity,
            originCountry = domain.originCountry,
            voteCount = domain.voteCount,
            firstAirDate = domain.firstAirDate,
            backdropPath = domain.backdropPath,
            originalLanguage = domain.originalLanguage,
            id = domain.id,
            voteAverage = domain.voteAverage,
            overview = domain.overview,
            posterPath = domain.posterPath
        )
    }
}