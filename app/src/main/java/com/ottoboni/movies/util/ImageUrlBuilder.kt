package com.ottoboni.movies.util

import com.ottoboni.movies.BuildConfig.TMDB_API_KEY
import com.ottoboni.movies.BuildConfig.TMDB_API_QUERY
import com.ottoboni.movies.BuildConfig.TMDB_BASE_IMGAGE_URL

object ImageUrlBuilder {
    fun buildImageUrl(posterPath: String): String {
        return TMDB_BASE_IMGAGE_URL + posterPath + TMDB_API_QUERY + TMDB_API_KEY
    }
}