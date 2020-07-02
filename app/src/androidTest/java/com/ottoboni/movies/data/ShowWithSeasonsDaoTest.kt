package com.ottoboni.movies.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.database.dao.ShowWithSeasonsDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShowWithSeasonsDaoTest {

    private lateinit var testDatabase: AppDatabase
    private lateinit var showWithSeasonsDao: ShowWithSeasonsDao
    private lateinit var showDao: ShowDao
    private lateinit var seasonDao: SeasonDao

    @Before
    fun setupDatabase() {
        AppDatabase.setupDatabase(true)
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.getInstance(context)

        showDao = testDatabase.showDao()
        seasonDao = testDatabase.seasonDao()
        showWithSeasonsDao = testDatabase.showWithSeasonsDao()
    }

    @Test
    fun getShowWithSeason() = runBlocking {
        showDao.insert(DataUtils.showEntity)
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())

        val showsWithSeasons = showWithSeasonsDao.getShowsWithSeasons()

        assertNotNull(showsWithSeasons)
        assertEquals(DataUtils.seasonList, showsWithSeasons?.first()?.seasons)
    }

    @Test
    fun getShowWithSeasonByShow() = runBlocking {
        showDao.insert(*DataUtils.showList.toTypedArray())
        seasonDao.insert(*DataUtils.seasonList.toTypedArray())

        val showWithSeasons =
            showWithSeasonsDao.getShowWithSeasonsById(DataUtils.SEASON_WITH_EPISODES_SHOW_ID)

        assertNotNull(showWithSeasons)
        assertEquals(DataUtils.seasonList, showWithSeasons?.seasons)
    }
}