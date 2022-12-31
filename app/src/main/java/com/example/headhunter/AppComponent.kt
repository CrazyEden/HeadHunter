package com.example.headhunter

import com.example.headhunter.di.BindsModule
import com.example.headhunter.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,BindsModule::class])
interface AppComponent {
    fun inject(vacanciesFragment: VacanciesFragment)
}