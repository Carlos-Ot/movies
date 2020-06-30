package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.domain.model.factory.ShowFactory
import javax.inject.Inject

class ShowRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val showFactory: ShowFactory
) : ShowRemoteDataSource {
    override suspend fun fetchPopularShows(page: Int, region: String) =
        apiClient.fetchPopularShowsAsync(page, region)
            ?.results
            ?.ifEmpty { null }
            ?.map(showFactory::make)

    override suspend fun fetchShow(showId: Int) =
        apiClient.fetchShowAsync(showId)
            ?.let(showFactory::make)
}