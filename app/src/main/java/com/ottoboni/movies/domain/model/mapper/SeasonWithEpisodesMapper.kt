package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.data.source.local.entity.SeasonWithEpisodes
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.Season
import javax.inject.Inject

class SeasonWithEpisodesMapper @Inject constructor(
    private val episodeMapper: Mapper<EpisodeEntity, Episode>
) : RelationMapper<SeasonWithEpisodes, Season> {
    override fun toDomain(entity: SeasonWithEpisodes) =
        Season(
            airDate = entity.season.airDate,
            episodeCount = entity.season.episodeCount,
            id = entity.season.id,
            name = entity.season.name,
            overview = entity.season.overview,
            posterPath = entity.season.posterPath,
            seasonNumber = entity.season.seasonNumber,
            showId = entity.season.showId,
            episodes = entity.episodes.map(episodeMapper::toDomain)
        )
}