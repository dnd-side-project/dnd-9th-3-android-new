package com.dnd_9th_3_android.gooding.login.di

import com.dnd_9th_3_android.gooding.login.data.KaKaoLoginImpl
import com.dnd_9th_3_android.gooding.login.data.KaKaoLoginInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(ActivityComponent::class)
@Module
abstract class KaKaoLoginModule {

    @Binds
    abstract fun bindKaKao(impl : KaKaoLoginImpl) : KaKaoLoginInterface
}