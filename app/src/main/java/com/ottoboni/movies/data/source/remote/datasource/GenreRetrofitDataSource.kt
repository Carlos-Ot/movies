package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.factory.ModelFactory
import retrofit2.HttpException
import javax.inject.Inject

class GenreRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val genreFactory: ModelFactory<GenreResponse, Genre>,
    exceptionFactory: ModelFactory<HttpException, Exception>
) : RemoteDataSource(exceptionFactory), GenreRemoteDataSource {
    override suspend fun fetchGenres() = safeCall {
        apiClient.fetchGenresAsync()
            ?.genres
            ?.ifEmpty { null }
            ?.map(genreFactory::make)
    }
}