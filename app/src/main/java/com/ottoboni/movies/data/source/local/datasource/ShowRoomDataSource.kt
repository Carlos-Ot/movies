package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.database.dao.ShowWithSeasonsDao
import com.ottoboni.movies.data.source.local.entity.ShowEntity
import com.ottoboni.movies.data.source.local.entity.ShowWithSeasons
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.mapper.Mapper
import com.ottoboni.movies.domain.model.mapper.RelationMapper
import javax.inject.Inject

class ShowRoomDataSource @Inject constructor(
    private val showDao: ShowDao,
    private val showWithSeasonsDao: ShowWithSeasonsDao,
    private val showMapper: Mapper<ShowEntity, Show>,
    private val showWithSeasonsMapper: RelationMapper<ShowWithSeasons, Show>
) : ShowLocalDataSource {
    override suspend fun getShowsWithSeasons() =
        showWithSeasonsDao.getShowsWithSeasons()?.map(showWithSeasonsMapper::toDomain)

    override suspend fun getShowWithSeasonById(showId: Int) =
        showWithSeasonsDao.getShowWithSeasonsById(showId)?.let(showWithSeasonsMapper::toDomain)

    override suspend fun save(show: Show) = showDao.insert(showMapper.fromDomain(show))

    override suspend fun delete(show: Show) = showDao.delete(showMapper.fromDomain(show))

    override suspend fun getAll() = showDao.getAll()?.map(showMapper::toDomain)

    override suspend fun getById(id: Int) = showDao.getById(id)?.let(showMapper::toDomain)
}