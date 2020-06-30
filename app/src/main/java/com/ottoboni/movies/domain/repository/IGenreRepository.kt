package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.domain.model.Genre

interface IGenreRepository {
    suspend fun loadGenres(): List<Genre>?

    suspend fun save(genre: Genre)

    suspend fun delete(genre: Genre)

    suspend fun getAll(): List<Genre>?

    suspend fun getById(id: Int): Genre?
}