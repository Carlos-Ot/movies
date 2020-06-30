package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import javax.inject.Inject

class EpisodeRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val episodeFactory: EpisodeFactory
) : EpisodeRemoteDataSource {
    override suspend fun fetchEpisode(
        showId: Int,
        seasonId: Int,
        episodeId: Int
    ) = apiClient.fetchEpisodeAsync(showId, seasonId, episodeId)
        ?.let(episodeFactory::make)
}