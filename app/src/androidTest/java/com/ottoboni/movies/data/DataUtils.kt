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
        "Arriving at Winterfell, Jon and Daenerys struggle to unite a divided North. Jon Snow gets some big news.",
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
            "Arriving at Winterfell, Jon and Daenerys struggle to unite a divided North. Jon Snow gets some big news.",
            1551825,
            "/aeLFDgp0J9140lS4nWUgZZRK6Rp.jpg",
            7.563F,
            107971
        ),
        EpisodeEntity(
            2,
            "2019-04-21",
            "A Knight of the Seven Kingdoms",
            "The battle at Winterfell is approaching. Jaime is confronted with the consequences of the past. A tense interaction between Sansa and Daenerys follows.",
            1551826,
            "/vJ8H9WtzbJGfArfdycc4nagQVRU.jpg",
            7.788F,
            107971
        ),
        EpisodeEntity(
            3,
            "2019-04-28",
            "The Long Night",
            "The Night King and his army have arrived at Winterfell and the great battle begins. Arya looks to prove her worth as a fighter.",
            1551827,
            "/k69MmmwXIy1ywEQDZME4des9aRJ.jpg",
            7.647F,
            107971
        ),
        EpisodeEntity(
            4,
            "2019-05-05",
            "The Last of the Starks",
            "In the wake of a costly victory, Jon and Daenerys look to the south as Tyrion eyes a compromise that could save countless lives.",
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
        overview = "The Great War has come, the Wall has fallen and the Night King's army of the dead marches towards Westeros. The end is here, but who will take the Iron Throne?",
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
            overview = "Trouble is brewing in the Seven Kingdoms of Westeros. For the driven inhabitants of this visionary world, control of Westeros' Iron Throne holds the lure of great power. But in a land where the seasons can last a lifetime, winter is coming...and beyond the Great Wall that protects them, an ancient evil has returned. In Season One, the story centers on three primary areas: the Stark and the Lannister families, whose designs on controlling the throne threaten a tenuous peace; the dragon princess Daenerys, heir to the former dynasty, who waits just over the Narrow Sea with her malevolent brother Viserys; and the Great Wall--a massive barrier of ice where a forgotten danger is stirring.",
            posterPath = "/wgfKiqzuMrFIkU1M68DDDY8kGC1.jpg",
            seasonNumber = 1,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2012-04-01",
            episodeCount = 10,
            id = 3625,
            name = "Season 2",
            overview = "The cold winds of winter are rising in Westeros...war is coming...and five kings continue their savage quest for control of the all-powerful Iron Throne. With winter fast approaching, the coveted Iron Throne is occupied by the cruel Joffrey, counseled by his conniving mother Cersei and uncle Tyrion. But the Lannister hold on the Throne is under assault on many fronts. Meanwhile, a new leader is rising among the wildings outside the Great Wall, adding new perils for Jon Snow and the order of the Night's Watch.",
            posterPath = "/9xfNkPwDOqyeUvfNhs1XlWA0esP.jpg",
            seasonNumber = 2,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2013-03-31",
            episodeCount = 10,
            id = 3626,
            name = "Season 3",
            overview = "Duplicity and treachery...nobility and honor...conquest and triumph...and, of course, dragons. In Season 3, family and loyalty are the overarching themes as many critical storylines from the first two seasons come to a brutal head. Meanwhile, the Lannisters maintain their hold on King's Landing, though stirrings in the North threaten to alter the balance of power; Robb Stark, King of the North, faces a major calamity as he tries to build on his victories; a massive army of wildlings led by Mance Rayder march for the Wall; and Daenerys Targaryen--reunited with her dragons--attempts to raise an army in her quest for the Iron Throne.",
            posterPath = "/5MkZjRnCKiIGn3bkXrXfndEzqOU.jpg",
            seasonNumber = 3,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2014-04-06",
            episodeCount = 10,
            id = 3628,
            name = "Season 4",
            overview = "The War of the Five Kings is drawing to a close, but new intrigues and plots are in motion, and the surviving factions must contend with enemies not only outside their ranks, but within.",
            posterPath = "/jXIMScXE4J4EVHUba1JgxZnWbo4.jpg",
            seasonNumber = 4,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2015-04-12",
            episodeCount = 10,
            id = 62090,
            name = "Season 5",
            overview = "Following the shocking developments at the conclusion of season five, survivors from all parts of Westeros and Essos regroup to press forward, inexorably, towards their uncertain individual fates. Familiar faces will forge new alliances to bolster their strategic chances at survival, while new characters will emerge to challenge the balance of power in the east, west, north and south.",
            posterPath = "/7Q1Hy1AHxAzA2lsmzEMBvuWTX0x.jpg",
            seasonNumber = 5,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2016-04-24",
            episodeCount = 10,
            id = 71881,
            name = "Season 6",
            overview = "Following the shocking developments at the conclusion of season five, survivors from all parts of Westeros and Essos regroup to press forward, inexorably, towards their uncertain individual fates. Familiar faces will forge new alliances to bolster their strategic chances at survival, while new characters will emerge to challenge the balance of power in the east, west, north and south.",
            posterPath = "/p1udLh0gfqyZFmXBGa393gk8go5.jpg",
            seasonNumber = 6,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2017-07-16",
            episodeCount = 7,
            id = 81266,
            name = "Season 7",
            overview = "The long winter is here. And with it comes a convergence of armies and attitudes that have been brewing for years.",
            posterPath = "/oX51n32QyHeFP5kErksemJsJljL.jpg",
            seasonNumber = 7,
            showId = 1399
        ),
        SeasonEntity(
            airDate = "2019-04-14",
            episodeCount = 6,
            id = 107971,
            name = "Season 8",
            overview = "The Great War has come, the Wall has fallen and the Night King's army of the dead marches towards Westeros. The end is here, but who will take the Iron Throne?",
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
        overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
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
            overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
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
            overview = "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
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
            overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            posterPath = "/fki3kBlwJzFp8QohL43g9ReV455.jpg"
        )
    )

    const val SEASON_WITH_EPISODES_ID = 107971
    const val SEASON_WITH_EPISODES_SHOW_ID = 1399
}