package com.ottoboni.movies.domain.datasource

interface BaseLocalDataSource<T> {

    suspend fun save(domain: T)

    suspend fun saveAll(vararg domain: T)

    suspend fun update(domain: T)

    suspend fun delete(domain: T)

    suspend fun getAll(): List<T>

    suspend fun getById(id: Int): T
}