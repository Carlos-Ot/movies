package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.cache.ListCache
import com.ottoboni.movies.data.source.local.datasource.ShowLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRemoteDataSource
import com.ottoboni.movies.data.source.remote.model.enums.MediaType
import com.ottoboni.movies.data.source.remote.model.enums.TimeWindow
import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowRepository @Inject constructor(
    private val localDataSource: ShowLocalDataSource,
    private val remoteDataSource: ShowRemoteDataSource,
    private val popularCache: ListCache<Show>,
    private val trendingCache: ListCache<Show>
) : IShowRepository {
    override suspend fun getShowsWithSeasons() = localDataSource.getShowsWithSeasons()

    override suspend fun getShowWithSeasonsById(showId: Int) =
        localDataSource.getShowWithSeasonById(showId)

    override suspend fun fetchPopular(page: Int, region: String) =
        popularCache.items
            .takeIf { it.size >= page * PAGE_SIZE }
            ?.subList(fromIndex = (page * PAGE_SIZE - PAGE_SIZE), toIndex = page * PAGE_SIZE)
            ?: remoteDataSource.fetchPopular(page, region)
                ?.also { popularCache += it }
                ?.distinctBy { it.id }
                ?.take(PAGE_SIZE)

    override suspend fun fetchTrending(page: Int) =
        trendingCache.items
            .takeIf { it.size >= page * PAGE_SIZE }
            ?.subList(fromIndex = (page * PAGE_SIZE - PAGE_SIZE), toIndex = page * PAGE_SIZE)
            ?: remoteDataSource.fetchTrending(page, MediaType.TV, TimeWindow.WEEK)
                ?.also { trendingCache += it }
                ?.distinctBy { it.id }
                ?.take(PAGE_SIZE)

    override suspend fun fetchBy(showId: Int) =
        if (popularCache.items.isEmpty())
            remoteDataSource.fetchBy(showId)?.also(popularCache::plusAssign)
        else popularCache.items.firstOrNull { it.id == showId }
            ?: remoteDataSource.fetchBy(showId)?.also(popularCache::plusAssign)

    // TODO: When the user saves a Show we need to save the seasons if available on cache.
    override suspend fun save(show: Show) = localDataSource.save(show)

    override suspend fun delete(show: Show) = localDataSource.delete(show)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getBy(id: Int) = localDataSource.getById(id)

    companion object {
        const val STARTING_PAGE = 1
        const val PAGE_SIZE = 20
    }
}
