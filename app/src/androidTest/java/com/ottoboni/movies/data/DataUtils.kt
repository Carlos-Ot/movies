package com.ottoboni.movies.data

import com.ottoboni.movies.data.source.local.entity.EpisodeEntity
import com.ottoboni.movies.data.source.local.entity.GenreEntity
import com.ottoboni.movies.data.source.local.entity.SeasonEntity
import com.ottoboni.movies.data.source.local.entity.ShowEntity

object DataUtils {
    val episodeEntity = EpisodeEntity(
        0,
        "2019-04-14",
        "Winterfell",
        "",
        1551825,
        "/aeLFDgp0J9140lS4nWUgZZRK6Rp.jpg",
        7.563F,
        107971
    )

    val episodeList = listOf(
        EpisodeEntity(
            1,
            "2019-04-14",
            "Winterfell",
            "",
            1551825,
            "/aeLFDgp0J9140lS4nWUgZZRK6Rp.jpg",
            7.563F,
            107971
        ),
        EpisodeEntity(
            2,
            "2019-04-21",
            "A Knight of the Seven Kingdoms",
            "",
            1551826,
            "/vJ8H9WtzbJGfArfdycc4nagQVRU.jpg",
            7.788F,
            107971
        ),
        EpisodeEntity(
            3,
            "2019-04-28",
            "The Long Night",
            "",
            1551827,
            "/k69MmmwXIy1ywEQDZME4des9aRJ.jpg",
            7.647F,
            107971
        ),
        EpisodeEntity(
            4,
            "2019-05-05",
            "The Last of the Starks",
            "",
            1551828,
            "/qe508xwVVO7cF2oWzfJp52StkTk.jpg",
            7.095F,
            107971
        ),
        EpisodeEntity(
            5,
            "2019-05-12",
            "The Bells",
            "Daenerys brings her forces to King's Landing.",
            1551829,
            "/xF9hOs5h9sc37oWdtF4RPHq8dXA.jpg",
            6.6F,
            107971
        ),
        EpisodeEntity(
            6,
            "2019-05-19",
            "Episode 6",
            "",
            1551830,
            "",
            0F,
            107971
        )
    )

    val genreEntity = GenreEntity(
        1,
        "Action"
    )

    val genreList = listOf(
        GenreEntity(1, "Action"),
        GenreEntity(2, "Drama"),
        GenreEntity(3, "Romance"),
        GenreEntity(4, "Comedy"),
        GenreEntity(5, "Terror")
    )

    val seasonEntity = SeasonEntity(
        airDate = "2019-04-14",
        episodeCount = 6,
        id = 107971,
        name = "Season 8",
        overview = "",
        posterPath = "/3OcQhbrecf4F4pYss2gSirTGPvD.jpg",
        seasonNumber = 8,
        showId = 1399
    )

    val seasonList = listOf(
        SeasonEntity(
            airDate = "2010-12-05",
            episodeCount = 10,
            id = 3624,
            name = "Season 1",
            overview = "",
            posterPath = "/wgfKiqzuMrFIkU1M68DDDY8kGC1.jpg",
            seasonNumber = 1,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2012-04-01",
            episodeCount = 10,
            id = 3625,
            name = "Season 2",
            overview = "",
            posterPath = "/9xfNkPwDOqyeUvfNhs1XlWA0esP.jpg",
            seasonNumber = 2,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2013-03-31",
            episodeCount = 10,
            id = 3626,
            name = "Season 3",
            overview = "",
            posterPath = "/5MkZjRnCKiIGn3bkXrXfndEzqOU.jpg",
            seasonNumber = 3,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2014-04-06",
            episodeCount = 10,
            id = 3628,
            name = "Season 4",
            overview = "",
            posterPath = "/jXIMScXE4J4EVHUba1JgxZnWbo4.jpg",
            seasonNumber = 4,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2015-04-12",
            episodeCount = 10,
            id = 62090,
            name = "Season 5",
            overview = "",
            posterPath = "/7Q1Hy1AHxAzA2lsmzEMBvuWTX0x.jpg",
            seasonNumber = 5,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2016-04-24",
            episodeCount = 10,
            id = 71881,
            name = "Season 6",
            overview = "",
            posterPath = "/p1udLh0gfqyZFmXBGa393gk8go5.jpg",
            seasonNumber = 6,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2017-07-16",
            episodeCount = 7,
            id = 81266,
            name = "Season 7",
            overview = "",
            posterPath = "/oX51n32QyHeFP5kErksemJsJljL.jpg",
            seasonNumber = 7,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2019-04-14",
            episodeCount = 6,
            id = 107971,
            name = "Season 8",
            overview = "",
            posterPath = "/3OcQhbrecf4F4pYss2gSirTGPvD.jpg",
            seasonNumber = 8,
            showId = 1399
        )
    )

    val showEntity = ShowEntity(
        originalName = "Game of Thrones",
        genreIds = listOf(18, 10759, 10765),
        name = "Game of Thrones",
        popularity = 830.725F,
        originCountry = listOf("US"),
        voteCount = 5835,
        firstAirDate = "2011-04-17",
        backdropPath = "/qsD5OHqW7DSnaQ2afwz8Ptht1Xb.jpg",
        originalLanguage = "en",
        id = 1399,
        voteAverage = 8.2F,
        overview = "",
        posterPath = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
    )

    val showList = listOf(
        ShowEntity(
            originalName = "Game of Thrones",
            genreIds = listOf(18, 10759, 10765),
            name = "Game of Thrones",
            popularity = 830.725F,
            originCountry = listOf("US"),
            voteCount = 5835,
            firstAirDate = "2011-04-17",
            backdropPath = "/qsD5OHqW7DSnaQ2afwz8Ptht1Xb.jpg",
            originalLanguage = "en",
            id = 1399,
            voteAverage = 8.2F,
            overview = "",
            posterPath = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
        ),
        ShowEntity(
            originalName = "ワンパンマン",
            genreIds = listOf(16, 35, 10759),
            name = "One-Punch Man",
            popularity = 595.645F,
            originCountry = listOf("JP"),
            voteCount = 339,
            firstAirDate = "2015-10-04",
            backdropPath = "/s0w8JbuNNxL1YgaHeDWih12C3jG.jpg",
            originalLanguage = "ja",
            id = 63926,
            voteAverage = 8.1F,
            overview = "",
            posterPath = "/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg"
        ),
        ShowEntity(
            originalName = "The Flash",
            genreIds = listOf(18, 10765),
            name = "The Flash",
            popularity = 349.464F,
            originCountry = listOf("US"),
            voteCount = 2657,
            firstAirDate = "2014-10-07",
            backdropPath = "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg",
            originalLanguage = "en",
            id = 60735,
            voteAverage = 6.6F,
            overview = "",
            posterPath = "/fki3kBlwJzFp8QohL43g9ReV455.jpg"
        )
    )

    const val SEASON_WITH_EPISODES_ID = 107971
    const val SEASON_WITH_EPISODES_SHOW_ID = 1399
}
