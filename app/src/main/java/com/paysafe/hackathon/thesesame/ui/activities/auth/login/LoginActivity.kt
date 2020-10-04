package com.paysafe.hackathon.thesesame.ui.activities.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.databinding.ActivityLoginBinding
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthListener
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthViewModel
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthViewModelFactory
import com.paysafe.hackathon.thesesame.internal.utils.IntentUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by closestKodein()
    private val factory: AuthViewModelFactory by instance()


    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        IntentUtils.startMainActivity(this)
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            IntentUtils.startMainActivity(this)
        }
    }
}