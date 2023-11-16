package com.leslienan.core_network

import com.leslienan.core_network.adapter.ModelConverterFactory
import com.leslienan.core_network.interceptor.LogInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val CONN_TIMEOUT = 10L //连接超时时间,单位  秒
    private const val READ_TIMEOUT = 600L //读超时时间,10分钟
    private const val WRITE_TIMEOUT = 600L //写超时时间,10分钟

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetConst.baseUrl) // 设置OkHttpclient
            .client(okHttp)
            .addConverterFactory(ModelConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        /*val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // OkHttp日志拦截器
            builder.addInterceptor(HttpLoggingInterceptor())
            builder.addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    val strLength: Int = message.length
                    var start = 0
                    var end = 2000
                    for (i in 0..99) {
                        //剩下的文本还是大于规定长度则继续重复截取并输出
                        if (strLength > end) {
                            Log.d("okhttp", message.substring(start, end))
                            start = end
                            end += 2000
                        } else {
                            Log.d("okhttp", message.substring(start, strLength))
                            break
                        }
                    }
                }

            }).setLevel(HttpLoggingInterceptor.Level.BODY))
        }*/
        val d = Dispatcher() // 突破默认单个 host 5个请求的上限，提升网络访问速度
        d.maxRequestsPerHost = 20
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor())
//                .addInterceptor(ParamsInterceptor(BaseApplication.INSTANCE))
//                .addInterceptor(WiresharkInterceptor())
//                .addNetworkInterceptor(HttpLoggingInterceptor(BaseApplication.INSTANCE)) //TODO 测试下 是否是在9.0上才出现的问题
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
            .dispatcher(d)
        return builder.build()
    }
}

inline fun createRequestBody(data: JSONObject.() -> Unit = {}): RequestBody {
    val jsonObject = JSONObject()
    try {
        data.invoke(jsonObject)
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}