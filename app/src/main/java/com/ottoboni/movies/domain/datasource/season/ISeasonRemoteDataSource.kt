package com.ottoboni.movies.domain.datasource.season

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import kotlinx.coroutines.Deferred

interface ISeasonRemoteDataSource {
    fun fetchSeasonAsync(showId: Int, seasonNumber: Int): Deferred<SeasonResponse>
}