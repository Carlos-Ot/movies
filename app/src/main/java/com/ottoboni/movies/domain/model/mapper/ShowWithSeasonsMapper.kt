package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.ShowWithSeasons
import com.ottoboni.movies.domain.model.Show

object ShowWithSeasonsMapper {
    fun toDomain(entity: ShowWithSeasons): Show {
        return Show(
            originalName = entity.show.originalName,
            genres = emptyList(),
            genreIds = entity.show.genreIds,
            name = entity.show.name,
            popularity = entity.show.popularity,
            originCountry = entity.show.originCountry,
            voteCount = entity.show.voteCount,
            firstAirDate = entity.show.firstAirDate,
            backdropPath = entity.show.backdropPath,
            originalLanguage = entity.show.originalLanguage,
            id = entity.show.id,
            voteAverage = entity.show.voteAverage,
            overview = entity.show.overview,
            posterPath = entity.show.posterPath,
            seasons = entity.seasons.map { SeasonMapper.toDomain(it) }
        )
    }
}