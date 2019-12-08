package com.ottoboni.movies.domain.datasource.episode

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import kotlinx.coroutines.Deferred

interface IEpisodeRemoteDataSource {
    fun fetchEpisodeAsync(showId: Int, seasonId: Int, episodeId: Int): Deferred<EpisodeResponse>
}