package com.ottoboni.movies.data.source.remote

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.data.source.remote.network.TmdbApi
import com.ottoboni.movies.domain.datasource.show.IShowRemoteDataSource
import kotlinx.coroutines.Deferred

class ShowRemoteDataSource(private val apiClient: TmdbApi) : IShowRemoteDataSource {
    override fun fetchPopularAsync(page: Int, region: String): Deferred<ApiResponse> {
        return apiClient.popular(page, region)
    }

    override fun fetchShowAsync(showId: Int): Deferred<ShowResponse> {
        return apiClient.show(showId)
    }
}