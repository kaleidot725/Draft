package jp.kaleidot725.emomemo

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import jp.kaleidot725.emomemo.model.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class EmomemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDI()
        setupFlipper()
    }

    private fun setupDI() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }
    }

    private fun setupFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.start()
        }
    }
}
