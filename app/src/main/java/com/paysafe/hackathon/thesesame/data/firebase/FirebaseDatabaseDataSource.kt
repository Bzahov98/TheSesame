package com.paysafe.hackathon.thesesame.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.paysafe.hackathon.thesesame.data.model.User

class FirebaseDatabaseDataSource {
    private val firebaseDatabase: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    val reference
        get() = firebaseDatabase.reference

    fun writeNewUser(userId: String, email: String?, name: String = "") {
        val user = User(userId, name, email)
        reference.child("users").child(userId).setValue(user)
    }
}