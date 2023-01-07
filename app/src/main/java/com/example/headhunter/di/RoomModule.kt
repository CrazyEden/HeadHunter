package com.example.headhunter.di

import android.content.Context
import com.example.data.reps.AppDatabase
import com.example.data.reps.VacanciesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        context: Context
    ): AppDatabase {
        return AppDatabase.getInstance(context)
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): VacanciesDao {
        return appDatabase.vacanciesDao()
    }
}