package com.ottoboni.movies.data.source.local

import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.domain.datasource.episode.IEpisodeLocalDataSource
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.mapper.EpisodeMapper

class EpisodeLocalDataSource(private val episodeDao: EpisodeDao) : IEpisodeLocalDataSource {
    override suspend fun getBySeason(seasonId: Int): List<Episode> {
        return episodeDao.getBySeason(seasonId).map { EpisodeMapper.toDomain(it) }
    }

    override suspend fun save(domain: Episode) {
        episodeDao.insert(EpisodeMapper.fromDomain(domain))
    }

    override suspend fun saveAll(vararg domain: Episode) {
        val episodeList = domain.map { EpisodeMapper.fromDomain(it) }
        episodeDao.insert(*episodeList.toTypedArray())
    }

    override suspend fun update(domain: Episode) {
        episodeDao.update(EpisodeMapper.fromDomain(domain))
    }

    override suspend fun delete(domain: Episode) {
        episodeDao.delete(EpisodeMapper.fromDomain(domain))
    }

    override suspend fun getAll(): List<Episode> {
        return episodeDao.getAll().map { EpisodeMapper.toDomain(it) }
    }

    override suspend fun getById(id: Int): Episode {
        return EpisodeMapper.toDomain(episodeDao.getById(id))
    }
}