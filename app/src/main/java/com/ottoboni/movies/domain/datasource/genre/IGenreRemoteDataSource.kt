package com.ottoboni.movies.domain.datasource.genre

import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import kotlinx.coroutines.Deferred

interface IGenreRemoteDataSource {
    fun fetchGenresAsync(): Deferred<GenreApiResponse>
}