package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.domain.model.Season

interface SeasonRemoteDataSource {
    suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season?
}
