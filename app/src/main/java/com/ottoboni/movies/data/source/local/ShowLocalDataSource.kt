package com.ottoboni.movies.data.source.local

import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.database.dao.ShowWithSeasonsDao
import com.ottoboni.movies.domain.datasource.show.IShowLocalDataSource
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.mapper.ShowMapper
import com.ottoboni.movies.domain.model.mapper.ShowWithSeasonsMapper

class ShowLocalDataSource(
    private val showDao: ShowDao,
    private val showWithSeasonsDao: ShowWithSeasonsDao
) : IShowLocalDataSource {
    override suspend fun getShowsWithSeasons(): List<Show> {
        return showWithSeasonsDao.getShowsWithSeasons().map { ShowWithSeasonsMapper.toDomain(it) }
    }

    override suspend fun getShowWithSeasonById(showId: Int): Show {
        return ShowWithSeasonsMapper.toDomain(showWithSeasonsDao.getShowWithSeasonsById(showId))
    }

    override suspend fun save(domain: Show) {
        showDao.insert(ShowMapper.fromDomain(domain))
    }

    override suspend fun saveAll(vararg domain: Show) {
        val showList = domain.map { ShowMapper.fromDomain(it) }
        showDao.insert(*showList.toTypedArray())
    }

    override suspend fun update(domain: Show) {
        showDao.update(ShowMapper.fromDomain(domain))
    }

    override suspend fun delete(domain: Show) {
        showDao.delete(ShowMapper.fromDomain(domain))
    }

    override suspend fun getAll(): List<Show> {
        return showDao.getAll().map { ShowMapper.toDomain(it) }
    }

    override suspend fun getById(id: Int): Show {
        return ShowMapper.toDomain(showDao.getById(id))
    }
}