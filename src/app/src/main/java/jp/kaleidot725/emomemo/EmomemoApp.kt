package jp.kaleidot725.emomemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class EmomemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
