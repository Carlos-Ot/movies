package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.factory.ModelFactory
import retrofit2.HttpException
import javax.inject.Inject

class SeasonRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val seasonFactory: ModelFactory<SeasonResponse, Season>,
    exceptionFactory: ModelFactory<HttpException, Exception>
) : RemoteDataSource(exceptionFactory), SeasonRemoteDataSource {
    override suspend fun fetchSeason(showId: Int, seasonNumber: Int) = safeCall {
        apiClient.fetchSeasonAsync(showId, seasonNumber)
            ?.let(seasonFactory::make)
    }
}