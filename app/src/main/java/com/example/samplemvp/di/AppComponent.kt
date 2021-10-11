package com.example.samplemvp.di

import com.example.samplemvp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LoginModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}