package com.ottoboni.movies.data.source.local.cache

import com.ottoboni.movies.domain.model.Genre
import javax.inject.Inject

class GenreCache @Inject constructor() : ListCache<Genre> {

    private val _items = mutableListOf<Genre>()
    override var items: List<Genre>
        get() = _items
        set(value) {
            clear()
            _items.addAll(value.distinctBy { it.id })
        }

    override fun plusAssign(value: List<Genre>) {
        items = items.plus(value)
    }

    override fun plusAssign(value: Genre) {
        items = items.plus(listOf(value))
    }

    override fun clear() = _items.clear()
}