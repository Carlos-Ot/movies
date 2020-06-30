package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.data.source.local.database.dao.GenreDao
import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.mapper.Mapper
import javax.inject.Inject

class GenreRoomDataSource @Inject constructor(
    private val genreDao: GenreDao,
    private val genreMapper: Mapper<GenreEntity, Genre>
) :
    GenreLocalDataSource {
    override suspend fun save(genre: Genre) = genreDao.insert(genreMapper.fromDomain(genre))

    override suspend fun delete(genre: Genre) = genreDao.delete(genreMapper.fromDomain(genre))

    override suspend fun getAll() = genreDao.getAll()?.map(genreMapper::toDomain)

    override suspend fun getById(id: Int) = genreDao.getById(id)?.let(genreMapper::toDomain)
}