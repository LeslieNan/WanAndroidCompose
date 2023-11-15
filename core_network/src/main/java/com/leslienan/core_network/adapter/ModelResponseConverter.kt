package com.leslienan.core_network.adapter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.leslienan.core_network.BaseNetModel
import com.leslienan.core_network.NetworkException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import java.lang.reflect.Type


/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/23.
 * PS:
 */
class ModelResponseConverter<T>(val gson: Gson, val adapter: TypeAdapter<T>, val type: Type) :
    Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        return try {
            val body = value.string().replace("\"data\":null,", "").replace("\"data\": null,", "")
            Log.e("json", "convert: $body")
            val responseData = gson.fromJson<BaseNetModel<T>>(body, BaseNetModel::class.java)
            val model = gson.fromJson<T>(gson.toJson(responseData.data), type)
            if (responseData.errorCode == 0) {
                //对特殊的返回类型进行处理
                when (type) {
                    NullResponse::class.java -> {
                        NullResponse as T
                    }
                    JSONObject::class.java -> {
                        JSONObject(responseData.data as Map<*, *>) as T
                    }
                    else -> {
                        model
                    }
                }
            } else {
                throw NetworkException(responseData.errorCode, responseData.errorMsg, responseData)
            }
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            throw e
        } finally {
            value.close()
        }
    }


}