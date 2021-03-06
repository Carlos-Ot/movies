package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.entity.ShowEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShowDaoTest {

    private lateinit var testDatabase: AppDatabase
    private lateinit var showDao: ShowDao

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)

        showDao = testDatabase.showDao()
    }

    @Test
    fun insertAndGetShow() = runBlocking {
        showDao.insert(DataUtils.showEntity)

        val showFromDb = showDao.getById(DataUtils.showEntity.id)

        assertEquals(DataUtils.showEntity, showFromDb)
    }

    @Test
    fun insertListAndGet() = runBlocking {
        showDao.insert(*DataUtils.showList.toTypedArray())

        val showsFromDb = showDao.getAll() ?: emptyList()

        assertTrue(showsFromDb.isNotEmpty())
        assertTrue(showsFromDb.containsAll(DataUtils.showList))
    }

    @Test
    fun deleteShow() = runBlocking {
        showDao.insert(*DataUtils.showList.toTypedArray())

        showDao.delete(DataUtils.showList.first())

        val showsFromDb = showDao.getAll() ?: emptyList()

        assertTrue(showsFromDb.isNotEmpty())
        assertFalse(showsFromDb.contains(DataUtils.showList.first()))
    }

    @Test
    fun updateShow() = runBlocking {
        showDao.insert(DataUtils.showEntity)

        val newShow = ShowEntity(
            originalName = "ワンパンマン",
            genreIds = listOf(16, 35, 10759),
            name = "One-Punch Man",
            popularity = 595.645F,
            originCountry = listOf("JP"),
            voteCount = 339,
            firstAirDate = "2015-10-04",
            backdropPath = "/s0w8JbuNNxL1YgaHeDWih12C3jG.jpg",
            originalLanguage = "ja",
            id = 1399,
            voteAverage = 8.1F,
            overview = "",
            posterPath = "/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg"
        )

        showDao.update(newShow)

        val showFromDb = showDao.getById(newShow.id)

        assertEquals(newShow, showFromDb)
    }
}
