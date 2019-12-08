package com.ottoboni.movies.data.source.remote

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.network.TmdbApi
import com.ottoboni.movies.domain.datasource.episode.IEpisodeRemoteDataSource
import kotlinx.coroutines.Deferred

class EpisodeRemoteDataSource(private val apiClient: TmdbApi) : IEpisodeRemoteDataSource {
    override fun fetchEpisodeAsync(showId: Int, seasonId: Int, episodeId: Int): Deferred<EpisodeResponse> {
        return apiClient.episode(showId, seasonId, episodeId)
    }
}