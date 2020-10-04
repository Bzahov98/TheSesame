package com.paysafe.hackathon.thesesame.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class FirebaseCloudDataSource {

    private val firebaseCloud: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }
}