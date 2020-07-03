package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.domain.model.Episode

interface EpisodeRemoteDataSource {
    suspend fun fetchEpisode(showId: Int, seasonId: Int, episodeId: Int): Episode?
}
