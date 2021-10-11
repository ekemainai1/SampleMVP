package com.example.samplemvp.di

import com.example.samplemvp.contract.MainActivityMVP
import com.example.samplemvp.model.LoginModel
import com.example.samplemvp.presenter.MainActivityPresenter
import com.example.samplemvp.repo.LoginRepository
import com.example.samplemvp.repo.LoginRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideMainActivityPresenter(model: MainActivityMVP.Model): MainActivityMVP.Presenter {
        return MainActivityPresenter(model)
    }

    @Provides
    fun provideMainActivityModel(loginRepository: LoginRepository): MainActivityMVP.Model {
        return LoginModel(loginRepository)
    }

    @Provides
    fun provideLoginRepository(): LoginRepository{
        return LoginRepositoryImpl()
    }
}