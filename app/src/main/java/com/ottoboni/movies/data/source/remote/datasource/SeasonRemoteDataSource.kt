package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
import kotlinx.coroutines.Deferred

interface SeasonRemoteDataSource {
    suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season?
}