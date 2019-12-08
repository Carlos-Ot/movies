package com.ottoboni.movies.data.source.local

import com.ottoboni.movies.data.source.local.database.dao.GenreDao
import com.ottoboni.movies.domain.datasource.genre.IGenreLocalDataSource
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.mapper.GenreMapper

class GenreLocalDataSource(private val genreDao: GenreDao) : IGenreLocalDataSource {
    override suspend fun save(domain: Genre) {
        genreDao.insert(GenreMapper.fromDomain(domain))
    }

    override suspend fun saveAll(vararg domain: Genre) {
        val genreList = domain.map { GenreMapper.fromDomain(it) }
        genreDao.insert(*genreList.toTypedArray())
    }

    override suspend fun update(domain: Genre) {
        genreDao.update(GenreMapper.fromDomain(domain))
    }

    override suspend fun delete(domain: Genre) {
        genreDao.delete(GenreMapper.fromDomain(domain))
    }

    override suspend fun getAll(): List<Genre> {
        return genreDao.getAll().map { GenreMapper.toDomain(it) }
    }

    override suspend fun getById(id: Int): Genre {
        return GenreMapper.toDomain(genreDao.getById(id))
    }
}