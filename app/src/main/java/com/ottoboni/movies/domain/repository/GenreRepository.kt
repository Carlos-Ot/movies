package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.datasource.GenreLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.GenreRemoteDataSource
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.factory.GenreFactory
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val localDataSource: GenreLocalDataSource,
    private val remoteDataSource: GenreRemoteDataSource
) : IGenreRepository {
    override suspend fun save(genre: Genre) = localDataSource.save(genre)

    override suspend fun delete(genre: Genre) = localDataSource.delete(genre)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getById(id: Int) = localDataSource.getById(id)

    // TODO: Add Local Cache for Genres
    override suspend fun loadGenres() = remoteDataSource.fetchGenres()
}