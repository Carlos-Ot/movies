package com.ottoboni.movies.domain.factory

import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.domain.model.factory.ShowFactory
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ShowFactoryTest {

    private val emptyShowResponse = DataUtils.emptyShowResponse
    private val notEmptyShowResponse = DataUtils.notEmptyShowResponse

    private lateinit var factory: ModelFactory<ShowResponse, Show>

    @Before
    fun setup() {
        factory = ShowFactory()
    }

    @Test
    fun `test empty fields are turned to null`() {
        val result = factory.make(emptyShowResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNull(result.originalName)
        assertNull(result.genreIds)
        assertNull(result.name)
        assertNull(result.originCountry)
        assertNull(result.firstAirDate)
        assertNull(result.backdropUrl)
        assertNull(result.originalLanguage)
        assertNull(result.overview)
        assertNull(result.posterUrl)
    }

    @Test
    fun `test non-empty fields are parsed`() {
        val result = factory.make(notEmptyShowResponse)

        assertNotNull(result)
        assertEquals(notEmptyShowResponse.id, result.id)
        assertEquals(notEmptyShowResponse.originalName, result.originalName)
        assertEquals(notEmptyShowResponse.genreIds, result.genreIds)
        assertEquals(notEmptyShowResponse.name, result.name)
        assertEquals(notEmptyShowResponse.originCountry, result.originCountry)
        assertEquals(notEmptyShowResponse.firstAirDate, result.firstAirDate)
        assertTrue { result.backdropUrl!!.contains(notEmptyShowResponse.backdropPath!!) }
        assertEquals(notEmptyShowResponse.originalLanguage, result.originalLanguage)
        assertEquals(notEmptyShowResponse.overview, result.overview)
        assertTrue { result.posterUrl!!.contains(notEmptyShowResponse.posterPath!!) }
    }
}