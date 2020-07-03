package com.ottoboni.movies.domain.model.mapper

interface RelationMapper<E, D> {
    fun toDomain(entity: E): D
}
