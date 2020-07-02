package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.data.source.remote.model.enums.MediaType
import com.ottoboni.movies.data.source.remote.model.enums.TimeWindow
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.factory.ModelFactory
import javax.inject.Inject

class ShowRetrofitDataSource @Inject constructor(
    private val apiClient: TmdbApi,
    private val showFactory: ModelFactory<ShowResponse, Show>
) : ShowRemoteDataSource {
    override suspend fun fetchPopular(page: Int, region: String) =
        apiClient.fetchPopularShowsAsync(page, region)
            ?.results
            ?.ifEmpty { null }
            ?.map(showFactory::make)

    override suspend fun fetchTrending(mediaType: MediaType, timeWindow: TimeWindow) =
        apiClient.fetchTrendingShowsAsync(mediaType, timeWindow)
            ?.results
            ?.ifEmpty { null }
            ?.map(showFactory::make)

    override suspend fun fetchBy(showId: Int) =
        apiClient.fetchShowAsync(showId)
            ?.let(showFactory::make)
}