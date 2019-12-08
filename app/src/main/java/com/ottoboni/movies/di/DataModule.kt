package com.ottoboni.movies.di

import com.ottoboni.movies.data.repository.EpisodeRepository
import com.ottoboni.movies.data.repository.GenreRepository
import com.ottoboni.movies.data.repository.SeasonRepository
import com.ottoboni.movies.data.repository.ShowRepository
import com.ottoboni.movies.data.source.local.EpisodeLocalDataSource
import com.ottoboni.movies.data.source.local.GenreLocalDataSource
import com.ottoboni.movies.data.source.local.SeasonLocalDataSource
import com.ottoboni.movies.data.source.local.ShowLocalDataSource
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.remote.EpisodeRemoteDataSource
import com.ottoboni.movies.data.source.remote.GenreRemoteDataSource
import com.ottoboni.movies.data.source.remote.SeasonRemoteDataSource
import com.ottoboni.movies.data.source.remote.ShowRemoteDataSource
import com.ottoboni.movies.data.source.remote.network.ServiceClient
import com.ottoboni.movies.domain.datasource.episode.EpisodeDataSource
import com.ottoboni.movies.domain.datasource.episode.IEpisodeLocalDataSource
import com.ottoboni.movies.domain.datasource.episode.IEpisodeRemoteDataSource
import com.ottoboni.movies.domain.datasource.genre.GenreDataSource
import com.ottoboni.movies.domain.datasource.genre.IGenreLocalDataSource
import com.ottoboni.movies.domain.datasource.genre.IGenreRemoteDataSource
import com.ottoboni.movies.domain.datasource.season.ISeasonLocalDataSource
import com.ottoboni.movies.domain.datasource.season.ISeasonRemoteDataSource
import com.ottoboni.movies.domain.datasource.season.SeasonDataSource
import com.ottoboni.movies.domain.datasource.show.IShowLocalDataSource
import com.ottoboni.movies.domain.datasource.show.IShowRemoteDataSource
import com.ottoboni.movies.domain.datasource.show.ShowDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        AppDatabase.setupDatabase(false)
        AppDatabase.getInstance(androidContext())
    }

    single { get<AppDatabase>().episodeDao() }

    single { get<AppDatabase>().genreDao() }

    single { get<AppDatabase>().seasonDao() }

    single { get<AppDatabase>().seasonWithEpisodesDao() }

    single { get<AppDatabase>().showDao() }

    single { get<AppDatabase>().showWithSeasonsDao() }
}

val networkModule = module {
    single { ServiceClient.getOkHttpClient() }

    single { ServiceClient.getRetrofit(get()) }

    single { ServiceClient.getApiClient(get()) }
}

val dataSourceModule = module {
    single<IEpisodeLocalDataSource> { EpisodeLocalDataSource(get()) }
    single<IEpisodeRemoteDataSource> { EpisodeRemoteDataSource(get()) }
    single<EpisodeDataSource> { EpisodeRepository(get(), get()) }

    single<IGenreLocalDataSource> { GenreLocalDataSource(get()) }
    single<IGenreRemoteDataSource> { GenreRemoteDataSource(get()) }
    single<GenreDataSource> { GenreRepository(get(), get()) }

    single<ISeasonLocalDataSource> { SeasonLocalDataSource(get(), get()) }
    single<ISeasonRemoteDataSource> { SeasonRemoteDataSource(get()) }
    single<SeasonDataSource> { SeasonRepository(get(), get()) }

    single<IShowLocalDataSource> { ShowLocalDataSource(get(), get()) }
    single<IShowRemoteDataSource> { ShowRemoteDataSource(get()) }
    single<ShowDataSource> { ShowRepository(get(), get()) }
}