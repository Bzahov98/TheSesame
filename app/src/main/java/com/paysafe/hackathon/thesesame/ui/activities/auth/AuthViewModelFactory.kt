package com.paysafe.hackathon.thesesame.ui.activities.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paysafe.hackathon.thesesame.data.repositories.UserAuthRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: UserAuthRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}
