package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeasonDaoTest {
    private lateinit var seasonDao: SeasonDao
    private lateinit var testDatabase: AppDatabase

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)
        seasonDao = testDatabase.seasonDao()
    }

    @Test
    fun insertAndGetSeason() = runBlocking {
        seasonDao.insert(DataUtils.seasonEntity)

        val seasonFromDb = seasonDao.getById(DataUtils.seasonEntity.id)

        assertEquals(DataUtils.seasonEntity, seasonFromDb)
    }

    @Test
    fun insertAndGetSeasonByNumber() = runBlocking {
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())

        val seasonFromDb = seasonDao.getSeasonByNumber(DataUtils.seasonList.first().seasonNumber)

        assertEquals(DataUtils.seasonList.first(), seasonFromDb)
    }

    @Test
    fun insertListAndGetSeasons() = runBlocking {
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())

        val seasonsFromDb = seasonDao.getAll()

        assertEquals(DataUtils.seasonList, seasonsFromDb)
    }

    @Test
    fun deleteSeason() = runBlocking {
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())

        seasonDao.delete(DataUtils.seasonList.first())

        val seasonsFromDb = seasonDao.getAll()

        assertFalse(seasonsFromDb.contains(DataUtils.seasonList.first()))
    }

    @Test
    fun updateSeason() = runBlocking {
        seasonDao.insert(DataUtils.seasonEntity)

        val newSeason = SeasonEntity(
            airDate = "2015-04-12",
            episodeCount = 10,
            id = 107971,
            name = "Season 5",
            overview = "Following the shocking developments at the conclusion of season five, survivors from all parts of Westeros and Essos regroup to press forward, inexorably, towards their uncertain individual fates. Familiar faces will forge new alliances to bolster their strategic chances at survival, while new characters will emerge to challenge the balance of power in the east, west, north and south.",
            posterPath = "/7Q1Hy1AHxAzA2lsmzEMBvuWTX0x.jpg",
            seasonNumber = 5,
            showId = 1399
        )

        seasonDao.update(newSeason)

        val seasonFromDb = seasonDao.getById(newSeason.id)

        assertEquals(newSeason, seasonFromDb)
    }
}