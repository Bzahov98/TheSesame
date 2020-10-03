package com.paysafe.hackathon.thesesame.ui.utils

import android.content.Context
import android.content.Intent
import com.paysafe.hackathon.thesesame.ui.activities.auth.login.LoginActivity
import com.paysafe.hackathon.thesesame.ui.activities.main.MainActivity

fun Context.startHomeActivity() =
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }