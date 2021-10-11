package com.example.samplemvp.repo

import com.example.samplemvp.data.User

class LoginRepositoryImpl: LoginRepository {

    private var user: User = User("", "")

    override fun getUser(): User {
        return user
    }

    override fun saveUser(user: User) {
        val users = getUser()
        this.user = users
    }
}