package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.domain.model.Genre

interface GenreRemoteDataSource {
    suspend fun fetchGenres(): List<Genre>?
}
