package com.ottoboni.movies.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ottoboni.movies.data.source.local.entity.ShowWithSeasons

@Dao
interface ShowWithSeasonsDao {

    @Transaction
    @Query("SELECT * FROM tb_show")
    suspend fun getShowsWithSeasons(): List<ShowWithSeasons>?

    @Transaction
    @Query("SELECT * FROM tb_show WHERE id = :showId")
    suspend fun getShowWithSeasonsById(showId: Int): ShowWithSeasons?
}