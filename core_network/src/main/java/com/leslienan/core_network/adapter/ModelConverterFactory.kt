package com.leslienan.core_network.adapter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/23.
 * PS:
 */
class ModelConverterFactory(val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): ModelConverterFactory {
            return create(Gson())
        }

        fun create(gson: Gson): ModelConverterFactory {
            return ModelConverterFactory(gson)
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ModelRequestConverter(gson, adapter, type)
    }

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        return super.stringConverter(type, annotations, retrofit)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ModelResponseConverter(gson, adapter, type)
    }
}