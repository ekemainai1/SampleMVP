package com.example.samplemvp.repo

import com.example.samplemvp.data.User

interface LoginRepository {
    fun getUser(): User
    fun saveUser(user: User)
}