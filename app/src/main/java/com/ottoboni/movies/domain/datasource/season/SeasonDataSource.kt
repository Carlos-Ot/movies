package com.ottoboni.movies.domain.datasource.season

import com.ottoboni.movies.domain.datasource.BaseDataSource
import com.ottoboni.movies.domain.model.Season

interface SeasonDataSource : BaseDataSource<Season> {
    suspend fun getBySeasonNumber(seasonNumber: Int): Season
    suspend fun getSeasonsWithEpisodes(showId: Int): List<Season>
    suspend fun getSeasonWithEpidosesById(seasonId: Int): Season
    suspend fun fetchSeasonByNumber(showId: Int, seasonNumber: Int): Season
}