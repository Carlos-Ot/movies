package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ottoboni.movies.data.source.local.entity.ShowEntity

@Dao
interface ShowDao : BaseDao<ShowEntity> {

    @Query("SELECT * FROM tb_show")
    suspend fun getAll(): List<ShowEntity>?

    @Query("SELECT * FROM tb_show WHERE id = :showId")
    suspend fun getById(showId: Int): ShowEntity?
}