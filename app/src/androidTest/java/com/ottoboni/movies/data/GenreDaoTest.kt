package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.GenreDao
import com.ottoboni.movies.data.source.local.entity.GenreEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GenreDaoTest {
    private lateinit var genreDao: GenreDao
    private lateinit var testDatabase: AppDatabase

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)
        genreDao = testDatabase.genreDao()
    }

    @Test
    fun insertAndGetGenre() = runBlocking {
        genreDao.insert(DataUtils.genreEntity)

        val genreFromDb = genreDao.getById(DataUtils.genreEntity.id)

        assertEquals(DataUtils.genreEntity, genreFromDb)
    }

    @Test
    fun insertListAndGetGenres() = runBlocking {
        genreDao.insert(*DataUtils.genreList.toTypedArray())

        val genresFromDb = genreDao.getAll()

        assertEquals(DataUtils.genreList, genresFromDb)
    }

    @Test
    fun deleteGenre() = runBlocking {
        genreDao.insert(*DataUtils.genreList.toTypedArray())

        val genreFromDb = genreDao.getAll().first()

        genreDao.delete(genreFromDb)

        val genresFromDb = genreDao.getAll()

        assertFalse(genresFromDb.contains(genreFromDb))
    }

    @Test
    fun updateGenre() = runBlocking {
        genreDao.insert(DataUtils.genreEntity)

        val newGenre = GenreEntity(
            id = 1,
            name = "Romance"
        )

        genreDao.update(newGenre)

        val genreFromDb = genreDao.getById(DataUtils.genreEntity.id)

        assertEquals(newGenre, genreFromDb)
    }
}