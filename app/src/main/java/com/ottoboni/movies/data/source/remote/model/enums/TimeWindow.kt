package com.ottoboni.movies.data.source.remote.model.enums

import com.squareup.moshi.Json

enum class TimeWindow {
    @Json(name = "day")
    DAY,

    @Json(name = "week")
    WEEK
}