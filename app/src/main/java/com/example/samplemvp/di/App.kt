package com.example.samplemvp.di

import android.app.Application

class App: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .loginModule(LoginModule())
            .build()
    }

    fun getAppComponent(): AppComponent{
        return appComponent
    }
}