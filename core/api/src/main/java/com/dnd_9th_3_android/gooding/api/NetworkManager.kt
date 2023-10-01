package com.dnd_9th_3_android.gooding.api

import android.content.Context
import com.dnd_9th_3_android.gooding.api.feedApi.FeedRetrofitService
import com.dnd_9th_3_android.gooding.model.feed.Feed
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager() {
    companion object {
        private val instance : Retrofit? = null

        // 레트로핏 생성
        private fun getRetrofit(context:Context) : Retrofit {
            val tokenData = TokenSharedPreferences(context)
            val header = Interceptor{
                val original = it.request()
                if (tokenData.accessToken!=null){
                    val request = original.newBuilder()
                        .header("Authorization","Bearer ${tokenData.accessToken}")
                        .build()
                    it.proceed(request)
                }else {
                    it.proceed(original)
                }

            }

            return instance ?: Retrofit.Builder()
                .baseUrl("$baseUrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient(header))
                .build()
        }

        // 클라이언트 빌드
        private fun buildOkHttpClient(header: Interceptor) : OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Accept", "application/json")
                    }.build())
                }.also { client ->
                    client.addInterceptor(header)
                    //로그 기록 인터셉터 등록
                    val logInterceptor = HttpLoggingInterceptor()
                    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    client.addInterceptor(logInterceptor)
                }.build()
        }
    }

    fun getLoginApiService(context: Context) =
        getRetrofit(context).create(LoginRetrofitService::class.java)

    fun getUserApiService(context : Context) =
        getRetrofit(context).create(UserApiService::class.java)

    fun getFeedApiService(context: Context) =
        getRetrofit(context).create(FeedRetrofitService::class.java)
}