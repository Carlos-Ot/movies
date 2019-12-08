package com.ottoboni.movies.domain.model.parser

import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show

object ShowParser : Parser<ShowResponse, Show> {
    override fun parse(remote: ShowResponse): Show {
        return Show(
            originalName = remote.originalName,
            genres = emptyList(),
            genreIds = remote.genreIds,
            name = remote.name,
            popularity = remote.popularity,
            originCountry = remote.originCountry,
            voteAverage = remote.voteAverage,
            firstAirDate = remote.firstAirDate,
            backdropPath = remote.backdropPath,
            originalLanguage = remote.originalLanguage,
            id = remote.id,
            voteCount = remote.voteCount,
            overview = remote.overview,
            posterPath = remote.posterPath,
            seasons = emptyList()
        )
    }
}