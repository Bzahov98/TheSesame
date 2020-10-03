package com.paysafe.hackathon.thesesame

import android.app.Application
import android.content.res.Resources
import com.paysafe.hackathon.thesesame.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class TheSesameApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@TheSesameApplication))

        bind() from provider {
            HomeViewModelFactory()
        }
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