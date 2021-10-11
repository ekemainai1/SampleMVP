package com.example.samplemvp.contract

import com.example.samplemvp.data.User

interface MainActivityMVP {

    interface View{
        fun getName(): String
        fun getEmail(): String
        fun showInputError()

        fun setName(name: String)
        fun setEmail(email: String)
        fun showUserSavedMessage()
    }

    interface Presenter{
        fun setView(view: View)
        fun loginButtonClicked()
        fun getCurrentUser()
    }

    interface Model{
        fun createUser(name: String, email: String)
        fun getUser(): User
    }
}