package com.ottoboni.movies.domain.datasource.episode

import com.ottoboni.movies.domain.datasource.BaseLocalDataSource
import com.ottoboni.movies.domain.model.Episode

interface IEpisodeLocalDataSource : BaseLocalDataSource<Episode> {
    suspend fun getBySeason(seasonId: Int): List<Episode>
}