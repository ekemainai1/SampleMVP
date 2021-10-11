package com.example.samplemvp.data

data class User(val name: String, val email: String) {
    private var id: Int = 0

    @JvmName("setId1")
    fun setId(id: Int){
        this.id = id
    }
}