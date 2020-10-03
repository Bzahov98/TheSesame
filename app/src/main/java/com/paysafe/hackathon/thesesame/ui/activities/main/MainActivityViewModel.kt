package com.paysafe.hackathon.thesesame.ui.activities.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.paysafe.hackathon.thesesame.data.repositories.UserAuthRepository
import com.paysafe.hackathon.thesesame.ui.utils.startLoginActivity

class MainActivityViewModel(
    private val repository: UserAuthRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }
}