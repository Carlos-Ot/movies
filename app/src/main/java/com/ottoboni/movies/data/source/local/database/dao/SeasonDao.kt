package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ottoboni.movies.data.source.local.entity.SeasonEntity

@Dao
interface SeasonDao : BaseDao<SeasonEntity> {

    @Query("SELECT * FROM tb_season")
    suspend fun getAll(): List<SeasonEntity>?

    @Query("SELECT * FROM tb_season WHERE id = :seasonId")
    suspend fun getById(seasonId: Int): SeasonEntity?

    @Query("SELECT * FROM tb_season WHERE season_number = :seasonNumber")
    suspend fun getSeasonByNumber(seasonNumber: Int): SeasonEntity?
}