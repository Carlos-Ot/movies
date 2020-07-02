package com.ottoboni.movies.domain.model.factory

import com.ottoboni.movies.BuildConfig.TMDB_API_KEY
import com.ottoboni.movies.BuildConfig.TMDB_API_QUERY
import com.ottoboni.movies.BuildConfig.TMDB_BASE_IMGAGE_URL
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowFactory @Inject constructor() : ModelFactory<ShowResponse, Show> {
    override fun make(remote: ShowResponse) =
        Show(
            originalName = remote.originalName,
            genres = emptyList(),
            genreIds = remote.genreIds,
            name = remote.name,
            popularity = remote.popularity,
            originCountry = remote.originCountry,
            voteAverage = remote.voteAverage,
            firstAirDate = remote.firstAirDate,
            backdropUrl = buildImageUrl(remote.backdropPath),
            originalLanguage = remote.originalLanguage,
            id = remote.id,
            voteCount = remote.voteCount,
            overview = remote.overview,
            posterUrl = buildImageUrl(remote.posterPath),
            seasons = emptyList()
        )

    private fun buildImageUrl(path: String?): String? =
        path?.let { "$TMDB_BASE_IMGAGE_URL$it?$TMDB_API_QUERY=$TMDB_API_KEY" }
}