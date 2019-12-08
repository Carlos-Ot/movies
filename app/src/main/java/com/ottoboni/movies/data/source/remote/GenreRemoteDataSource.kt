package com.ottoboni.movies.data.source.remote

import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import com.ottoboni.movies.data.source.remote.network.TmdbApi
import com.ottoboni.movies.domain.datasource.genre.IGenreRemoteDataSource
import kotlinx.coroutines.Deferred

class GenreRemoteDataSource(private val apiClient: TmdbApi) : IGenreRemoteDataSource {
    override fun fetchGenresAsync(): Deferred<GenreApiResponse> {
        return apiClient.genres()
    }
}