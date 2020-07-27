package jp.kaleidot725.emomemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.ActivityMainBinding
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController get() = findNavController(R.id.nav_host_fragment)
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(setOf(R.id.homeFragment)).setDrawerLayout(drawer_layout).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupNavController()
        setupViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also { binding ->
            setSupportActionBar(binding.actionBar)
            binding.viewModel = viewModel
            binding.navView.setupWithNavController(navController)
        }
    }

    private fun setupViewModel() {
        viewModel.notebooks.observe(this, Observer { notebooks -> setupNavDrawer(notebooks) })
        viewModel.init()
    }

    private fun setupNavController() {
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, _, _ ->
            when (controller.currentDestination?.id) {
                R.id.topFragment -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
    }

    private fun setupNavDrawer(notebooks: List<NotebookEntity>) {
        val menu = binding.navView.menu
        menu.clear()

        menu.addSubMenu(getString(R.string.navigation_drawer_sub_menu_action)).apply {
            this.add(getString(R.string.navigation_drawer_item_add_notebook)).apply {
                this.setOnMenuItemClickListener {
                    navController.navigate(R.id.action_global_addNotebookDialogFragment)
                    true
                }
            }
            this.add(getString(R.string.navigation_drawer_item_remove_notebook)).apply {
                this.setOnMenuItemClickListener {
                    navController.navigate(R.id.action_global_deleteNotebookDialogFragment)
                    true
                }
            }
        }

        menu.addSubMenu(getString(R.string.navigation_drawer_sub_menu_notebooks)).also { subMenu ->
            notebooks.forEach { notebook ->
                subMenu.add(notebook.title).also { menuItem ->
                    menuItem.setOnMenuItemClickListener {
                        viewModel.selectNotebook(notebook.id)
                        false
                    }
                }
            }
        }
    }
}
