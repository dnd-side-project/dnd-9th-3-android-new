package com.dnd_9th_3_android.gooding

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        // kakao sdk 초기화
        KakaoSdk.init(this, "a244014544b5323246b6853ca1d8ca93")
    }

    companion object {
        lateinit var instance: GlobalApplication

        fun getInstance(): Application {
            return instance
        }
    }
}