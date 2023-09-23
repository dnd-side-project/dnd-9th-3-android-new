package com.dnd_9th_3_android.gooding.trash.di

import android.util.Log
import com.dnd_9th_3_android.gooding.api.baseUrl
import com.dnd_9th_3_android.gooding.data.api.KakaoMapService
import com.dnd_9th_3_android.gooding.api.kakaoMapUrl
import com.dnd_9th_3_android.gooding.data.api.RecordFeedService
import com.dnd_9th_3_android.gooding.data.api.SearchFeedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule() {
    private var kakaoMapService: KakaoMapService? = null
    private var searchFeedService: SearchFeedService? = null
    private var recordFeedService: RecordFeedService? = null

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Log.d("API", message)
    }.apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideKakaoApiInterceptor(): ApiInterceptor = ApiInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(apiInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun getClient(
        client: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("$kakaoMapUrl/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun getKakaoApiService(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ): KakaoMapService {
        kakaoMapService?.let {
            return it
        } ?: run {
            val client = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(apiInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            kakaoMapService = Retrofit.Builder().baseUrl("$kakaoMapUrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(KakaoMapService::class.java)

            return kakaoMapService!!
        }
    }

    @Provides
    @Singleton
    fun searchFeedApiService(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): SearchFeedService {
        searchFeedService?.let {
            return it
        } ?: run {
            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            searchFeedService = Retrofit.Builder().baseUrl("$baseUrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(SearchFeedService::class.java)

            return searchFeedService!!
        }
    }

    @Provides
    @Singleton
    fun recordFeedApiService(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): RecordFeedService {
        recordFeedService?.let {
            return it
        } ?: run {
            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            recordFeedService = Retrofit.Builder().baseUrl("$baseUrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RecordFeedService::class.java)

            return recordFeedService!!
        }
    }

    class ApiInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .header("Accept-Language", getLanguage())
                .header("Authorization", "KakaoAK 66e15e5cd7fdce2de67e28ec53aad52a")
                .build()

            return chain.proceed(request)
        }

        private fun getLanguage() = Locale.getDefault().language
    }
}