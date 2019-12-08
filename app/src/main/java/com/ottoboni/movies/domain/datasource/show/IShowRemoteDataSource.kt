package com.ottoboni.movies.domain.datasource.show

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import kotlinx.coroutines.Deferred

interface IShowRemoteDataSource {
    fun fetchPopularAsync(page: Int, region: String): Deferred<ApiResponse>
    fun fetchShowAsync(showId: Int): Deferred<ShowResponse>
}