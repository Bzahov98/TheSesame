package com.paysafe.hackathon.thesesame

import android.app.Application
import android.content.res.Resources
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseAuthDataSource
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseCloudDataSource
import com.paysafe.hackathon.thesesame.data.firebase.FirebaseDatabaseDataSource
import com.paysafe.hackathon.thesesame.data.repositories.ItemsRepository
import com.paysafe.hackathon.thesesame.data.repositories.UserAuthRepository
import com.paysafe.hackathon.thesesame.ui.activities.auth.AuthViewModelFactory
import com.paysafe.hackathon.thesesame.ui.activities.main.MainActivityViewModelFactory
import com.paysafe.hackathon.thesesame.ui.fragments.home.HomeViewModelFactory
import com.paysafe.hackathon.thesesame.ui.fragments.myArtItems.MyArtItemsViewModelFactory
import com.paysafe.hackathon.thesesame.ui.fragments.portfolio.PortfolioViewModelFactory
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

        //Firebase Data sources
        bind() from singleton { FirebaseAuthDataSource() }
        bind() from singleton { FirebaseDatabaseDataSource() }
        bind() from singleton { FirebaseCloudDataSource() }

        // Repositories
        bind() from singleton { UserAuthRepository(instance(),instance(),instance()) }
        bind() from singleton { ItemsRepository(instance(),instance(),instance()) }

        // ViewModels Factories
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory() }
        bind() from provider { MyArtItemsViewModelFactory(instance()) }
        bind() from provider { MainActivityViewModelFactory(instance()) }
        bind() from provider { PortfolioViewModelFactory(instance()) }
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