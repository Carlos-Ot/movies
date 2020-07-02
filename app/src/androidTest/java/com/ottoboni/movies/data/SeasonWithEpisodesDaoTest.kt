package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonWithEpisodesDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeasonWithEpisodesDaoTest {

    private lateinit var testDatabase: AppDatabase
    private lateinit var seasonWithEpisodeDao: SeasonWithEpisodesDao
    private lateinit var seasonDao: SeasonDao
    private lateinit var episodeDao: EpisodeDao

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)

        seasonDao = testDatabase.seasonDao()
        episodeDao = testDatabase.episodeDao()
        seasonWithEpisodeDao = testDatabase.seasonWithEpisodesDao()
    }

    @Test
    fun getSeasonWithEpisodes() = runBlocking {
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())
        episodeDao.insert(*DataUtils.episodeList.toTypedArray())

        val seasonWithEpisodes =
            seasonWithEpisodeDao.getSeasonWithEpisodes(DataUtils.SEASON_WITH_EPISODES_ID)

        assertNotNull(seasonWithEpisodes)
        assertEquals(DataUtils.episodeList, seasonWithEpisodes?.episodes)
    }

    @Test
    fun getSeasonWithEpisodesByShow() = runBlocking {
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())
        episodeDao.insert(*DataUtils.episodeList.toTypedArray())

        val seasonWithEpisodes =
            seasonWithEpisodeDao.getSeasonsWithEpisodesByShow(DataUtils.SEASON_WITH_EPISODES_SHOW_ID)

        val seasonFromDb =
            seasonWithEpisodes?.first { it.season.id == DataUtils.SEASON_WITH_EPISODES_ID }

        assertNotNull(seasonFromDb)
        assertEquals(DataUtils.episodeList, seasonFromDb?.episodes)
    }
}