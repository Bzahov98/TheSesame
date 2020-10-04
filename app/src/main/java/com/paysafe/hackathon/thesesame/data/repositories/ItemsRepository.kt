package com.paysafe.hackathon.thesesame.data.repositories

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseAuthDataSource
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseCloudDataSource
import com.paysafe.hackathon.thesesame.data.model.ArtItemData

class ItemsRepository(
    private val firebaseAuth: FirebaseAuthDataSource,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseCloudDataSource: FirebaseCloudDataSource
){
    private val TAG = "ItemsRepository"

    //TODO
    fun createNewItem(artItemData: ArtItemData) {
        Log.d(TAG, "createNewItem: $artItemData")
    }

    fun getAllMyItems(name: String, uid: Int) {
        Log.d(TAG, "getAllMyItems:")
    }

}