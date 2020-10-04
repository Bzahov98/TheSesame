package com.paysafe.hackathon.thesesame.data.repositories

import com.google.firebase.database.FirebaseDatabase
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseAuthDataSource
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseCloudDataSource
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseDatabaseDataSource
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthListener
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserAuthRepository(
    private val firebaseAuth: FirebaseAuthDataSource,
    private val firebaseDatabase: FirebaseDatabaseDataSource,
    private val firebaseCloudDataSource: FirebaseCloudDataSource
) {
    fun login(email: String, password: String): Completable {
        return firebaseAuth.login(email, password)
    }

    fun register(email: String, password: String, authListener: AuthListener?): Disposable {
        return firebaseAuth.register(email, password).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
//                    val currentUser = firebaseAuth.currentUser()
//                    if (currentUser != null) {
//                        firebaseDatabase.writeNewUser(
//                            currentUser.uid,
//                            currentUser.email,
//                        )
//                    }else authListener?.onFailure("current user is null")

                    authListener?.onSuccess()
                },
                {
                    authListener?.onFailure(it.message!!)
                },
            )
    }

    fun currentUser() = firebaseAuth.currentUser()

    fun logout() = firebaseAuth.logout()
}