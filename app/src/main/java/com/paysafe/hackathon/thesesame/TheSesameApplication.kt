package com.paysafe.hackathon.thesesame

import android.app.Application
import android.content.res.Resources
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseDataSource
import com.paysafe.hackathon.thesesame.data.repositories.UserAuthRepository
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthViewModelFactory
import com.paysafe.hackathon.thesesame.ui.activities.main.MainActivityViewModelFactory
import com.paysafe.hackathon.thesesame.ui.fragments.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TheSesameApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@TheSesameApplication))

        bind() from singleton { FirebaseDataSource() }
        bind() from singleton { UserAuthRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory() }
        bind() from provider { MainActivityViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        // PreferenceManager.setDefaultValues(this,R.xml.preferences,false)
        resourcesNew = resources

    }

    companion object {
        var resourcesNew: Resources? = null
        fun getAppResources(): Resources? {
            return resourcesNew
        }

        fun getAppString(id: Int): String {
            return resourcesNew!!.getString(id)
        }
    }
}