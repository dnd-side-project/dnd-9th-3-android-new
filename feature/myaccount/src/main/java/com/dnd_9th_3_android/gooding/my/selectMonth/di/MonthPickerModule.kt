package com.dnd_9th_3_android.gooding.my.selectMonth.di

import com.dnd_9th_3_android.gooding.my.selectMonth.data.MonthPickerImpl
import com.dnd_9th_3_android.gooding.my.selectMonth.data.MonthPickerInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MonthPickerModule {

    @Singleton
    @Binds
    abstract fun bindMonthPicker(impl : MonthPickerImpl) : MonthPickerInterface
}