package com.ottoboni.movies.domain.factory

import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.factory.GenreFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GenreFactoryTest {

    private val emptyGenreResponse = DataUtils.emptyGenreResponse
    private val notEmptyGenreResponse = DataUtils.notEmptyGenreResponse

    private lateinit var factory: ModelFactory<GenreResponse, Genre>

    @Before
    fun setup() {
        factory = GenreFactory()
    }

    @Test
    fun `test empty fields are turned to null`() {
        val result = factory.make(emptyGenreResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNull(result.name)
    }

    @Test
    fun `test non-empty fields are parsed`() {
        val result = factory.make(notEmptyGenreResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertEquals(notEmptyGenreResponse.name, result.name)
    }
}