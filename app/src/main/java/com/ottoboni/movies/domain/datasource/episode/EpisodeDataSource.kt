package com.ottoboni.movies.domain.datasource.episode

import com.ottoboni.movies.domain.datasource.BaseDataSource
import com.ottoboni.movies.domain.model.Episode

interface EpisodeDataSource : BaseDataSource<Episode> {
    suspend fun getBySeason(seasonId: Int): List<Episode>

    suspend fun loadEpisode(showId: Int, seasonId: Int, episodeId: Int): Episode
}