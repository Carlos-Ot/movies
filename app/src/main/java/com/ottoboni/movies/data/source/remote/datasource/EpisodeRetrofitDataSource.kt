package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import javax.inject.Inject

class EpisodeRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val episodeFactory: ModelFactory<EpisodeResponse, Episode>
) : EpisodeRemoteDataSource {
    override suspend fun fetchEpisode(
        showId: Int,
        seasonId: Int,
        episodeId: Int
    ) = apiClient.fetchEpisodeAsync(showId, seasonId, episodeId)
        ?.let(episodeFactory::make)
}