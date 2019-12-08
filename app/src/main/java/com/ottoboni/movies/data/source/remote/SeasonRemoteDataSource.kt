package com.ottoboni.movies.data.source.remote

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.data.source.remote.network.TmdbApi
import com.ottoboni.movies.domain.datasource.season.ISeasonRemoteDataSource
import kotlinx.coroutines.Deferred

class SeasonRemoteDataSource(private val apiClient: TmdbApi) : ISeasonRemoteDataSource {
    override fun fetchSeasonAsync(showId: Int, seasonNumber: Int): Deferred<SeasonResponse> {
        return apiClient.season(showId, seasonNumber)
    }
}