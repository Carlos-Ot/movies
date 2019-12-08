package com.ottoboni.movies.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ottoboni.movies.data.source.local.database.converters.BaseTypeConverters
import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.data.source.local.database.dao.GenreDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonWithEpisodesDao
import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.database.dao.ShowWithSeasonsDao
import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.data.source.local.entity.ShowEntity

@Database(
    entities = [
        EpisodeEntity::class,
        GenreEntity::class,
        SeasonEntity::class,
        ShowEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(BaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    // DAOs
    abstract fun episodeDao(): EpisodeDao

    abstract fun genreDao(): GenreDao

    abstract fun seasonDao(): SeasonDao

    abstract fun seasonWithEpisodesDao(): SeasonWithEpisodesDao

    abstract fun showDao(): ShowDao

    abstract fun showWithSeasonsDao(): ShowWithSeasonsDao

    companion object {
        private var TEST_DATABASE = false
        private const val databaseName = "app_database.db"

        private var instance: AppDatabase? = null

        private var lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (instance == null) {
                    if (TEST_DATABASE) {
                        instance = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                            .allowMainThreadQueries()
                            .build()
                    } else {
                        instance = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
                    }
                }
            }

            return instance!!
        }

        fun setupDatabase(isTesting: Boolean) {
            TEST_DATABASE = isTesting
        }
    }
}