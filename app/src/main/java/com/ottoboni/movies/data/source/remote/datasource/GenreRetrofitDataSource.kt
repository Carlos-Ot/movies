package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.factory.GenreFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import javax.inject.Inject

class GenreRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val genreFactory: GenreFactory
) : GenreRemoteDataSource {
    override suspend fun fetchGenres() =
        apiClient.fetchGenresAsync()
            ?.genres
            ?.ifEmpty { null }
            ?.map(genreFactory::make)
}