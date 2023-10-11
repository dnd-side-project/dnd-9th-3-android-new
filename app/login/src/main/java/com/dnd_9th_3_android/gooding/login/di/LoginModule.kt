package com.dnd_9th_3_android.gooding.login.di

import android.content.Context
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.LoginRepository
import com.dnd_9th_3_android.gooding.login.data.repository.GoogleLoginImpl
import com.dnd_9th_3_android.gooding.login.data.repository.KaKaoLoginImpl
import com.dnd_9th_3_android.gooding.login.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {

    @Binds
    @ViewModelScoped
    abstract fun provideLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ) : LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun bindKaKao(impl : KaKaoLoginImpl) : KaKaoLoginInterface

    @Binds
    @ViewModelScoped
    abstract fun bindGoogle(impl : GoogleLoginImpl) : GoogleLoginInterface

}