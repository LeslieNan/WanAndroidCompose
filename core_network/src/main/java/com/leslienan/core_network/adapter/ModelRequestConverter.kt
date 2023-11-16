package com.leslienan.core_network.adapter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/23.
 * PS:
 */
class ModelRequestConverter<T>(val gson: Gson, val adapter: TypeAdapter<T>, val type: Type) :
    Converter<T, RequestBody> {
    private val UTF_8 = Charset.forName("UTF-8")
    private val MEDIA_TYPE = "application/json; charset=utf-8".toMediaTypeOrNull()

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return buffer.readByteString().toRequestBody(MEDIA_TYPE)
    }
}