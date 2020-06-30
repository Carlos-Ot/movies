package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.domain.model.Genre

interface GenreLocalDataSource {
    suspend fun save(genre: Genre)

    suspend fun delete(genre: Genre)

    suspend fun getAll(): List<Genre>?

    suspend fun getById(id: Int): Genre?
}