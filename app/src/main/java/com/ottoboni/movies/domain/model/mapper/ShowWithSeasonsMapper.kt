package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.data.source.local.entity.ShowWithSeasons
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowWithSeasonsMapper @Inject constructor(
    private val seasonMapper: Mapper<SeasonEntity, Season>
) : RelationMapper<ShowWithSeasons, Show> {
    override fun toDomain(entity: ShowWithSeasons) =
        Show(
            originalName = entity.show.originalName,
            genres = emptyList(),
            genreIds = entity.show.genreIds,
            name = entity.show.name,
            popularity = entity.show.popularity,
            originCountry = entity.show.originCountry,
            voteCount = entity.show.voteCount,
            firstAirDate = entity.show.firstAirDate,
            backdropUrl = entity.show.backdropPath,
            originalLanguage = entity.show.originalLanguage,
            id = entity.show.id,
            voteAverage = entity.show.voteAverage,
            overview = entity.show.overview,
            posterUrl = entity.show.posterPath,
            seasons = entity.seasons.map(seasonMapper::toDomain)
        )
}