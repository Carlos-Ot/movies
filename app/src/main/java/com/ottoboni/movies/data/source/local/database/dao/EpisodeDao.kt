package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ottoboni.movies.data.source.local.entity.EpisodeEntity

@Dao
interface EpisodeDao : BaseDao<EpisodeEntity> {

    @Query("SELECT * FROM tb_episodes")
    suspend fun getAll(): List<EpisodeEntity>?

    @Query("SELECT * FROM tb_episodes WHERE id = :episodeId")
    suspend fun getById(episodeId: Int): EpisodeEntity?

    @Query("SELECT * FROM tb_episodes WHERE season_id = :seasonId")
    suspend fun getBySeason(seasonId: Int): List<EpisodeEntity>?
}