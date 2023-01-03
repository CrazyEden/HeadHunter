package com.example.headhunter.di

import com.example.data.reps.NetworkRepImpl
import com.example.domain.reps.NetworkRep
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindsModule {
    @Binds
    @Singleton
    fun provideNetworkResImpl(networkRepImpl: NetworkRepImpl): NetworkRep
}