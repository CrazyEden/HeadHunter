package com.example.headhunter.di

import com.example.data.reps.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

@Module
object NetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)


    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient:OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.hh.ru/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create()
    private val durationTimeout = Duration.ofSeconds(10000)
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(durationTimeout)
            .connectTimeout(durationTimeout)
            .writeTimeout(durationTimeout)
            .readTimeout(durationTimeout)
            .build()
}