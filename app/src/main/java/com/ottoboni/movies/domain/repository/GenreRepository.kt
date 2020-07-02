package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.cache.ListCache
import com.ottoboni.movies.data.source.local.datasource.GenreLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.GenreRemoteDataSource
import com.ottoboni.movies.domain.model.Genre
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val localDataSource: GenreLocalDataSource,
    private val remoteDataSource: GenreRemoteDataSource,
    private val genreCache: ListCache<Genre>
) : IGenreRepository {
    override suspend fun save(genre: Genre) = localDataSource.save(genre)

    override suspend fun delete(genre: Genre) = localDataSource.delete(genre)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getById(id: Int) = localDataSource.getById(id)

    override suspend fun loadGenres() =
        if (genreCache.items.isEmpty())
            remoteDataSource.fetchGenres()?.also(genreCache::plusAssign)
        else genreCache.items
}