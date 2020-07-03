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
            originalName = remote.originalName?.ifBlank { null },
            genres = emptyList(),
            genreIds = remote.genreIds?.ifEmpty { null },
            name = remote.name?.ifBlank { null },
            popularity = remote.popularity,
            originCountry = remote.originCountry?.ifEmpty { null },
            voteAverage = remote.voteAverage,
            firstAirDate = remote.firstAirDate?.ifBlank { null },
            backdropUrl = remote.backdropPath?.ifBlank{ null }?.let(::buildImageUrl),
            originalLanguage = remote.originalLanguage?.ifBlank { null },
            id = remote.id,
            voteCount = remote.voteCount,
            overview = remote.overview?.ifBlank { null },
            posterUrl = remote.posterPath?.ifBlank{ null }?.let(::buildImageUrl),
            seasons = emptyList()
        )

    private fun buildImageUrl(path: String?): String? =
        path?.let { "$TMDB_BASE_IMGAGE_URL$it?$TMDB_API_QUERY=$TMDB_API_KEY" }
}
