package jp.kaleidot725.emomemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import jp.kaleidot725.emomemo.R

class SettingActivity : AppCompatActivity(R.layout.activity_setting) {
    private val navController get() = findNavController(this, R.id.nav_host_fragment)
    private val appBarConfiguration get() = AppBarConfiguration(navController.graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}