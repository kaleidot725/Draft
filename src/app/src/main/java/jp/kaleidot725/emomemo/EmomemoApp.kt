package jp.kaleidot725.emomemo

import android.app.Application
import jp.kaleidot725.emomemo.model.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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
    }
}
