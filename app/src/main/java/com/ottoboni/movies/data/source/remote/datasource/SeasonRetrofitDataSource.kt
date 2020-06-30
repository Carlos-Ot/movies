package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.domain.model.factory.SeasonFactory
import javax.inject.Inject

class SeasonRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val seasonFactory: SeasonFactory
) : SeasonRemoteDataSource {
    override suspend fun fetchSeason(showId: Int, seasonNumber: Int) =
        apiClient.fetchSeasonAsync(showId, seasonNumber)
            ?.let(seasonFactory::make)
}