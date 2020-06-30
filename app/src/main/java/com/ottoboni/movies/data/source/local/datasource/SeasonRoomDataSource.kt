package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonWithEpisodesDao
import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.data.source.local.entity.SeasonWithEpisodes
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.mapper.Mapper
import com.ottoboni.movies.domain.model.mapper.RelationMapper
import javax.inject.Inject

class SeasonRoomDataSource @Inject constructor(
    private val seasonDao: SeasonDao,
    private val seasonWithEpisodesDao: SeasonWithEpisodesDao,
    private val seasonMapper: Mapper<SeasonEntity, Season>,
    private val seasonWithEpisodesMapper: RelationMapper<SeasonWithEpisodes, Season>
) : SeasonLocalDataSource {
    override suspend fun getBySeasonNumber(seasonNumber: Int) =
        seasonDao.getSeasonByNumber(seasonNumber)?.let(seasonMapper::toDomain)

    override suspend fun save(season: Season) = seasonDao.insert(seasonMapper.fromDomain(season))

    override suspend fun delete(season: Season) = seasonDao.delete(seasonMapper.fromDomain(season))

    override suspend fun getAll() = seasonDao.getAll()?.map(seasonMapper::toDomain)

    override suspend fun getById(id: Int) = seasonDao.getById(id)?.let(seasonMapper::toDomain)

    override suspend fun getSeasonsWithEpisodesByShow(showId: Int) =
        seasonWithEpisodesDao.getSeasonsWithEpisodesByShow(showId)
            ?.map(seasonWithEpisodesMapper::toDomain)

    override suspend fun getSeasonWithEpisodesById(seasonId: Int) =
        seasonWithEpisodesDao.getSeasonWithEpisodes(seasonId)
            ?.let(seasonWithEpisodesMapper::toDomain)
}