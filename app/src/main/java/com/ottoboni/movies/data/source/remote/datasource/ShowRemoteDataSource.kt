package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.model.enums.MediaType
import com.ottoboni.movies.data.source.remote.model.enums.TimeWindow
import com.ottoboni.movies.domain.model.Show

interface ShowRemoteDataSource {
    suspend fun fetchPopular(page: Int, region: String): List<Show>?

    suspend fun fetchTrending(mediaType: MediaType, timeWindow: TimeWindow): List<Show>?

    suspend fun fetchBy(showId: Int): Show?
}