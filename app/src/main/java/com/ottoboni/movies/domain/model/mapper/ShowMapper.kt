package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.ShowEntity
import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowMapper @Inject constructor() : Mapper<ShowEntity, Show> {
    override fun toDomain(entity: ShowEntity) =
        Show(
            originalName = entity.originalName,
            genres = emptyList(),
            genreIds = entity.genreIds,
            name = entity.name,
            popularity = entity.popularity,
            originCountry = entity.originCountry,
            voteCount = entity.voteCount,
            firstAirDate = entity.firstAirDate,
            backdropUrl = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            id = entity.id,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            posterUrl = entity.posterPath,
            seasons = emptyList()
        )

    override fun fromDomain(domain: Show) =
        ShowEntity(
            originalName = domain.originalName ?: "",
            genreIds = domain.genreIds ?: emptyList(),
            name = domain.name ?: "",
            popularity = domain.popularity ?: 0F,
            originCountry = domain.originCountry ?: emptyList(),
            voteCount = domain.voteCount ?: 0,
            firstAirDate = domain.firstAirDate ?: "",
            backdropPath = domain.backdropUrl ?: "",
            originalLanguage = domain.originalLanguage ?: "",
            id = domain.id ?: 0,
            voteAverage = domain.voteAverage ?: 0f,
            overview = domain.overview ?: "",
            posterPath = domain.posterUrl ?: ""
        )
}
