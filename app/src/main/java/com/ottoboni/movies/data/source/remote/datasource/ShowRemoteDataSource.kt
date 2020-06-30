package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.domain.model.Show

interface ShowRemoteDataSource {
    suspend fun fetchPopularShows(page: Int, region: String): List<Show>?

    suspend fun fetchShow(showId: Int): Show?
}