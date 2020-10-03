package com.paysafe.hackathon.thesesame.ui.activities.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}