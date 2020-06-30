package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ottoboni.movies.data.source.local.entity.GenreEntity

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @Query("SELECT * FROM tb_genres")
    suspend fun getAll(): List<GenreEntity>?

    @Query("SELECT * FROM tb_genres WHERE id = :genreId")
    suspend fun getById(genreId: Int): GenreEntity?
}