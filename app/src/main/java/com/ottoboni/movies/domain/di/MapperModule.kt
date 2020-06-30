package com.ottoboni.movies.domain.di

import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.data.source.local.entity.SeasonWithEpisodes
import com.ottoboni.movies.data.source.local.entity.ShowEntity
import com.ottoboni.movies.data.source.local.entity.ShowWithSeasons
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.mapper.EpisodeMapper
import com.ottoboni.movies.domain.model.mapper.GenreMapper
import com.ottoboni.movies.domain.model.mapper.Mapper
import com.ottoboni.movies.domain.model.mapper.RelationMapper
import com.ottoboni.movies.domain.model.mapper.SeasonMapper
import com.ottoboni.movies.domain.model.mapper.SeasonWithEpisodesMapper
import com.ottoboni.movies.domain.model.mapper.ShowMapper
import com.ottoboni.movies.domain.model.mapper.ShowWithSeasonsMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class MapperModule {
    @Binds
    abstract fun providesEpisodeMapper(impl: EpisodeMapper): Mapper<EpisodeEntity, Episode>

    @Binds
    abstract fun providesGenreMapper(impl: GenreMapper): Mapper<GenreEntity, Genre>

    @Binds
    abstract fun providesSeasonMapper(impl: SeasonMapper): Mapper<SeasonEntity, Season>

    @Binds
    abstract fun providesSeasonWithEpisodesMapper(impl: SeasonWithEpisodesMapper)
            : RelationMapper<SeasonWithEpisodes, Season>

    @Binds
    abstract fun providesShowMapper(impl: ShowMapper): Mapper<ShowEntity, Show>

    @Binds
    abstract fun providesShowWithSeasonsMapper(impl: ShowWithSeasonsMapper)
            : RelationMapper<ShowWithSeasons, Show>
}