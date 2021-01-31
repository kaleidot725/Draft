package jp.kaleidot725.emomemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private val navController get() = findNavController(this, R.id.nav_host_fragment)
    private val appBarConfiguration get() = AppBarConfiguration(navController.graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupNavController()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView<ActivitySettingBinding>(this, R.layout.activity_setting).also { binding ->
            setSupportActionBar(binding.actionBar)
        }
    }

    private fun setupNavController() {
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}