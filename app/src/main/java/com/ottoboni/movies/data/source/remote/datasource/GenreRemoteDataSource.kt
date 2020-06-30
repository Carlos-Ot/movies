package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import com.ottoboni.movies.domain.model.Genre
import kotlinx.coroutines.Deferred

interface GenreRemoteDataSource {
    suspend fun fetchGenres(): List<Genre>?
}