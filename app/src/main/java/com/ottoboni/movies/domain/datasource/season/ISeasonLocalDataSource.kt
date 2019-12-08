package com.ottoboni.movies.domain.datasource.season

import com.ottoboni.movies.domain.datasource.BaseLocalDataSource
import com.ottoboni.movies.domain.model.Season

interface ISeasonLocalDataSource : BaseLocalDataSource<Season> {
    suspend fun getBySeasonNumber(seasonNumber: Int): Season
    suspend fun getSeasonsWithEpisodesByShow(showId: Int): List<Season>
    suspend fun getSeasonWithEpisodesById(seasonId: Int): Season
}