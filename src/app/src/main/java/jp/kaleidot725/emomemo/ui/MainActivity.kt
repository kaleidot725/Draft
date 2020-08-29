package jp.kaleidot725.emomemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.ActivityMainBinding
import jp.kaleidot725.emomemo.extension.setActionBarVisibility
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.navigation_drawer_header.view.setting_image_button
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController get() = findNavController(R.id.nav_host_fragment)
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(setOf(R.id.homeFragment)).setOpenableLayout(drawer_layout).build()
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
            binding.navView.setupWithNavController(navController)
            binding.navView.inflateHeaderView(R.layout.navigation_drawer_header).also { drawerHeader ->
                drawerHeader.setting_image_button.setOnClickListener {
                    navController.navigate(R.id.action_global_settingFragment)
                    binding.drawerLayout.closeDrawers()
                }
            }
            binding.viewModel = viewModel
        }
    }

    private fun setupViewModel() {
        viewModel.notebooks.observe(this, Observer { notebooks ->
            setupNavDrawer(notebooks, viewModel.selectedNotebook.value)
        })

        viewModel.selectedNotebook.observe(this, Observer { notebook ->
            setupNavDrawer(viewModel.notebooks.value, notebook)
            this.title = notebook.title
        })
    }

    private fun setupNavController() {
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, _, _ ->
            val currentId = controller.currentDestination?.id ?: 0
            val isNotTopFragment = R.id.topFragment != currentId
            setActionBarVisibility(isNotTopFragment)

            val isHomeFragment = R.id.homeFragment != currentId
            val lockMode = if (isHomeFragment) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED
            binding.drawerLayout.setDrawerLockMode(lockMode)
        }
    }

    private fun setupNavDrawer(notebooks: List<NotebookEntity>?, selectedNotebook: NotebookEntity?) {
        // Initialize
        val menu = binding.navView.menu.apply { clear() }

        // Edit SubMenu
        val editSubMenu = menu.addSubMenu(getString(R.string.navigation_drawer_sub_menu_action))
        editSubMenu.add(getString(R.string.navigation_drawer_item_add_notebook)).apply {
            this.setOnMenuItemClickListener {
                navController.navigate(R.id.action_global_addNotebookDialogFragment)
                true
            }
        }

        editSubMenu.add(getString(R.string.navigation_drawer_item_remove_notebook)).apply {
            this.isEnabled = notebooks?.isNotEmpty() ?: true
            this.setOnMenuItemClickListener {
                navController.navigate(R.id.action_global_deleteNotebookDialogFragment)
                true
            }
        }

        // Notebook SubMenu
        val notebookSubMenu = menu.addSubMenu(getString(R.string.navigation_drawer_sub_menu_notebooks))
        notebooks?.map { notebook ->
            notebookSubMenu.add(notebook.title).also { menuItem ->
                menuItem.isChecked = notebook == selectedNotebook
                menuItem.setOnMenuItemClickListener {
                    viewModel.selectNotebook(notebook)
                    false
                }
            }
        }
    }
}
