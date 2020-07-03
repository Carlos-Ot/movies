package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EpisodeDaoTest {

    private lateinit var episodeDao: EpisodeDao
    private lateinit var testDatabase: AppDatabase

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)
        episodeDao = testDatabase.episodeDao()
    }

    @Test
    fun getEpisodeWithEmptyDatabase() = runBlocking {
        val episodes = episodeDao.getAll() ?: emptyList()

        assertTrue(episodes.isEmpty())
    }

    @Test
    fun getByIdWithEmptyDatabase() = runBlocking {
        val episode = episodeDao.getById(1)

        assertNull(episode)
    }

    @Test
    fun insertAndGetEpisode() = runBlocking {
        episodeDao.insert(DataUtils.episodeEntity)

        val episodeFromDb = episodeDao.getById(DataUtils.episodeEntity.id)

        assertEquals(DataUtils.episodeEntity, episodeFromDb)
    }

    @Test
    fun insertListAndGetEpisodes() = runBlocking {
        episodeDao.insert(*DataUtils.episodeList.toTypedArray())

        val episodesFromDb = episodeDao.getAll()

        assertEquals(DataUtils.episodeList, episodesFromDb)
    }

    @Test
    fun deleteEpisode() = runBlocking {
        episodeDao.insert(*DataUtils.episodeList.toTypedArray())

        val episodeFromDb = episodeDao.getAll()?.first()

        if (episodeFromDb != null) episodeDao.delete(episodeFromDb)
        else fail()

        val episodesFromDb = episodeDao.getAll() ?: emptyList()

        assertTrue(episodesFromDb.isNotEmpty())
        assertFalse(episodesFromDb.contains(episodeFromDb))
    }

    @Test
    fun updateEpisode() = runBlocking {
        episodeDao.insert(DataUtils.episodeEntity)

        val newEpisode = EpisodeEntity(
            0,
            "2019-04-00",
            "WinterHell",
            "ABC",
            1551825,
            "/aeLFDgp0J9140lS4nWUgZZRK6Rp.jpg",
            7.563F,
            107971
        )

        episodeDao.update(newEpisode)

        val episodeFromDb = episodeDao.getById(DataUtils.episodeEntity.id)

        assertEquals(newEpisode, episodeFromDb)
    }
}
