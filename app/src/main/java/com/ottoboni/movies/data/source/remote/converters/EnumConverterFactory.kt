package com.ottoboni.movies.data.source.remote.converters

import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object EnumConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Enum<*>, String>? =
        if (type is Class<*> && type.isEnum) {
            Converter { enum -> getSerializedNameValue(enum as Enum<*>) }
        } else null

    private fun <E : Enum<*>> getSerializedNameValue(e: E): String? =
        try {
            e.javaClass.getField(e.name).getAnnotation(Json::class.java)?.name
        } catch (e: NoSuchFieldException) {
            null
        }
}