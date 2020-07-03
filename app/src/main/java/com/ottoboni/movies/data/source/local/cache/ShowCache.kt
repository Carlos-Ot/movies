package com.ottoboni.movies.data.source.local.cache

import com.ottoboni.movies.domain.model.Show
import javax.inject.Inject

class ShowCache @Inject constructor() : ListCache<Show> {

    private val _items = mutableListOf<Show>()
    override var items: List<Show>
        get() = _items
        set(value) {
            clear()
            _items.addAll(value.distinctBy { it.id })
        }

    override fun plusAssign(value: List<Show>) {
        items = items.plus(value)
    }

    override fun plusAssign(value: Show) {
        items = items.plus(listOf(value))
    }

    override fun clear() = _items.clear()
}
