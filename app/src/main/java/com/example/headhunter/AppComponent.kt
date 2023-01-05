package com.example.headhunter

import android.content.Context
import com.example.headhunter.di.BindsModule
import com.example.headhunter.di.NetworkModule
import com.example.headhunter.di.RoomModule
import com.example.headhunter.presentation.vacanciesliset.VacanciesFragment
import com.example.headhunter.presentation.vacancyinfo.VacancyInfoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomModule::class, BindsModule::class])
interface AppComponent {
    fun inject(vacanciesFragment: VacanciesFragment)
    fun inject(VacancyInfoFragment: VacancyInfoFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}