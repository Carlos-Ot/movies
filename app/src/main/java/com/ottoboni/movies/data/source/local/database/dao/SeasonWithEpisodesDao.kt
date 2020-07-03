package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ottoboni.movies.data.source.local.entity.SeasonWithEpisodes

@Dao
interface SeasonWithEpisodesDao {

    @Transaction
    @Query("SELECT * FROM tb_season WHERE id = :seasonId")
    suspend fun getSeasonWithEpisodes(seasonId: Int): SeasonWithEpisodes?

    @Transaction
    @Query("SELECT * FROM tb_season WHERE show_id = :showId")
    suspend fun getSeasonsWithEpisodesByShow(showId: Int): List<SeasonWithEpisodes>?
}
