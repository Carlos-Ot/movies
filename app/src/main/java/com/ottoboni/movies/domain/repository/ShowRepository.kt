package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.datasource.ShowLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRemoteDataSource
import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowRepository @Inject constructor(
    private val localDataSource: ShowLocalDataSource,
    private val remoteDataSource: ShowRemoteDataSource
) : IShowRepository {
    override suspend fun getShowsWithSeasons() = localDataSource.getShowsWithSeasons()

    override suspend fun getShowWithSeasonsById(showId: Int) =
        localDataSource.getShowWithSeasonById(showId)

    // TODO: Save Popular List on Local Cache
    override suspend fun fetchPopular(page: Int, region: String) =
        remoteDataSource.fetchPopularShows(page, region)

    // TODO: Save Show on Local Cache
    override suspend fun fetchShow(showId: Int) = remoteDataSource.fetchShow(showId)

    // TODO: When the user saves a Show we need to save the seasons if available on cache.
    override suspend fun save(show: Show) = localDataSource.save(show)

    override suspend fun delete(show: Show) = localDataSource.delete(show)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getById(id: Int) = localDataSource.getById(id)
}