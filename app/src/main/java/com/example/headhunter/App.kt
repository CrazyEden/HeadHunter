package com.example.headhunter

import android.app.Application
import android.content.Context

class App:Application() {
    lateinit var appComponent:AppComponent
    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()
    }
}
val Context.appComponent:AppComponent
    get() = when(this){
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }