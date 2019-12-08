package com.ottoboni.movies.data.repository

import com.ottoboni.movies.domain.datasource.genre.GenreDataSource
import com.ottoboni.movies.domain.datasource.genre.IGenreLocalDataSource
import com.ottoboni.movies.domain.datasource.genre.IGenreRemoteDataSource
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.parser.GenreParser

class GenreRepository(
    private val localDataSource: IGenreLocalDataSource,
    private val remoteDataSource: IGenreRemoteDataSource
) : GenreDataSource {
    override suspend fun save(domain: Genre) {
        localDataSource.save(domain)
    }

    override suspend fun update(domain: Genre) {
        localDataSource.update(domain)
    }

    override suspend fun delete(domain: Genre) {
        localDataSource.delete(domain)
    }

    override suspend fun getAll(): List<Genre> {
        return localDataSource.getAll()
    }

    override suspend fun getById(id: Int): Genre {
        return localDataSource.getById(id)
    }

    override suspend fun loadGenres(): List<Genre> {
        val genresResponse = remoteDataSource.fetchGenresAsync().await()

        val genres = genresResponse.genres.map { GenreParser.parse(it) }

        localDataSource.saveAll(*genres.toTypedArray())

        return genres
    }
}