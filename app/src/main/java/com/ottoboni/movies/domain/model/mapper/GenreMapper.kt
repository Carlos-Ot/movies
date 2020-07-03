package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.domain.model.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreEntity, Genre> {
    override fun toDomain(entity: GenreEntity) =
        Genre(
            id = entity.id,
            name = entity.name
        )

    override fun fromDomain(domain: Genre) =
        GenreEntity(
            id = domain.id,
            name = domain.name
        )
}
