package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import kotlinx.coroutines.Deferred

interface EpisodeRemoteDataSource {
    suspend fun fetchEpisode(showId: Int, seasonId: Int, episodeId: Int): Episode?
}