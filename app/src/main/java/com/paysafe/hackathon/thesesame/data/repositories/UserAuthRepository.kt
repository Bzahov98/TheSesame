package com.paysafe.hackathon.thesesame.data.repositories

import com.paysafe.hackathon.thesesame.data.firebase.FirebaseDataSource

class UserAuthRepository (
    private val firebase: FirebaseDataSource
){
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}