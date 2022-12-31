package com.example.headhunter.di

import com.example.headhunter.domain.NetworkRepImpl
import com.example.headhunter.model.reps.NetworkRep
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindsModule {
    @Binds
    @Singleton
    fun provideNetworkResImpl(networkRepImpl: NetworkRepImpl):NetworkRep
}