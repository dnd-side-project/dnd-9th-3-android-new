package com.dnd_9th_3_android.gooding.data.di


import com.dnd_9th_3_android.gooding.data.dataMy.repository.MonthPickerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MonthPickerModule {
    @Provides
    @Singleton
    fun bindMonthPicker() = MonthPickerImpl()
}