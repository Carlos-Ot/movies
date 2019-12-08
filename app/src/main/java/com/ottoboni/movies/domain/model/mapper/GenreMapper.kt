package com.ottoboni.movies.domain.model.mapper

import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.domain.model.Genre

object GenreMapper : Mapper<GenreEntity, Genre> {
    override fun toDomain(entity: GenreEntity): Genre {
        return Genre(
            id = entity.id,
            name = entity.name
        )
    }

    override fun fromDomain(domain: Genre): GenreEntity {
        return GenreEntity(
            id = domain.id,
            name = domain.name
        )
    }
}