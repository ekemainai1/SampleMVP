package com.example.samplemvp.model

import com.example.samplemvp.contract.MainActivityMVP
import com.example.samplemvp.data.User
import com.example.samplemvp.repo.LoginRepository

class LoginModel constructor(
    private val loginRepository: LoginRepository
): MainActivityMVP.Model {

    override fun createUser(name: String, email: String) {
        loginRepository.saveUser(User(name = name, email = email))
    }

    override fun getUser(): User {
        return loginRepository.getUser()
    }
}