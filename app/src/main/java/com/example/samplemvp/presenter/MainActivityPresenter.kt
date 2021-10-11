package com.example.samplemvp.presenter

import com.example.samplemvp.contract.MainActivityMVP

class MainActivityPresenter constructor(
    private val model: MainActivityMVP.Model
): MainActivityMVP.Presenter {

    private lateinit var view: MainActivityMVP.View

    override fun setView(view: MainActivityMVP.View) {
        this.view = view
    }

    override fun loginButtonClicked() {
        if (view.getName().trim() == "" || view.getEmail().trim() == ""){
            view.showInputError()
        }else{
            model.createUser(view.getName(), view.getEmail())
            view.showUserSavedMessage()
        }
    }

    override fun getCurrentUser() {
        val user = model.getUser()
        view.setEmail(user.name)
        view.setEmail(user.email)
    }
}