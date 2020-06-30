package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.mapper.Mapper
import javax.inject.Inject

class EpisodeRoomDataSource @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val episodeMapper: Mapper<EpisodeEntity, Episode>
) : EpisodeLocalDataSource {
    override suspend fun getBySeason(seasonId: Int) =
        episodeDao.getBySeason(seasonId)?.map(episodeMapper::toDomain)

    override suspend fun save(episode: Episode) =
        episodeDao.insert(episodeMapper.fromDomain(episode))

    override suspend fun delete(episode: Episode) =
        episodeDao.delete(episodeMapper.fromDomain(episode))

    override suspend fun getAll() = episodeDao.getAll()?.map(episodeMapper::toDomain)

    override suspend fun getById(id: Int) = episodeDao.getById(id)?.let(episodeMapper::toDomain)
}