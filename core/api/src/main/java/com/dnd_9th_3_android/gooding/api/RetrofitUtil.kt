package com.dnd_9th_3_android.gooding.api

import androidx.lifecycle.LiveData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// SingleTon
object RetrofitUtil {
    private val instance : Retrofit? = null
    private var userToken : String? = null
    var userOauth : String? = null
    val LoginApiService : LoginRetrofitService by lazy {
        getRetrofit().create(LoginRetrofitService::class.java)
    }
    lateinit var userApiService: UserApiService

    // 레트로핏 생성
    private fun getRetrofit() : Retrofit{
        val header = Interceptor{
            val original = it.request()
            if (userToken!=null){
                val request = original.newBuilder()
                    .header("Authorization","Bearer $userToken")
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
    private fun buildOkHttpClient(header:Interceptor) : OkHttpClient {
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

    // 백엔드 서버로부터 받은 토큰 갱신
    fun setUserToken(newToken : String,userInfo : String){
        userToken = newToken
        userOauth = userInfo
        userApiService = getRetrofit().create(UserApiService::class.java)
    }
}