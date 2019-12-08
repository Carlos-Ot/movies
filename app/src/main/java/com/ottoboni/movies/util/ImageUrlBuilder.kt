package com.ottoboni.movies.util

import com.ottoboni.movies.BuildConfig

object ImageUrlBuilder {
    private val BASE_URL = "https://image.tmdb.org/t/p/w780"
    private val QUERY_API_KEY = "?api_key="

    fun buildImageUrl(posterPath: String): String {
        return BASE_URL + posterPath + QUERY_API_KEY + BuildConfig.API_KEY
    }
}