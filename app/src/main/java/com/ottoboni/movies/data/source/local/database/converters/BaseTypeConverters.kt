package com.ottoboni.movies.data.source.local.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class BaseTypeConverters {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)

        return adapter.toJson(list)
    }

    @TypeConverter
    fun toIntList(jsonString: String): List<Int> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)

        return adapter.fromJson(jsonString) ?: emptyList()
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)

        return adapter.toJson(list)
    }

    @TypeConverter
    fun toStringList(jsonString: String): List<String> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)

        return adapter.fromJson(jsonString) ?: emptyList()
    }
}