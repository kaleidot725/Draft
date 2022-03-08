package jp.kaleidot725.emomemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.emomemo.view.pages.Page
import jp.kaleidot725.emomemo.view.pages.main.MainPage
import jp.kaleidot725.emomemo.view.pages.memo.MemoDetailPage
import jp.kaleidot725.emomemo.view.pages.notebook.AddNotebookDialog
import jp.kaleidot725.emomemo.view.pages.notebook.DeleteNotebookDialog
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Page.Main.route) {
                    composable(route = Page.Main.route) {
                        MainPage(
                            viewModel = getNavComposeViewModel(),
                            onNavigateAddNotebook = { navController.navigate(Page.AddNoteBook.route) },
                            onNavigateRemoveNotebook = { navController.navigate(Page.RemoveNotebook.route) },
                            onNavigateMemoDetails = { navController.navigate(Page.Memo.route) }
                        )
                    }
                    composable(route = Page.Memo.route) {
                        MemoDetailPage(
                            viewModel = getNavComposeViewModel(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                    dialog(route = Page.AddNoteBook.route) {
                        AddNotebookDialog(
                            viewModel = getNavComposeViewModel()
                        )
                    }
                    dialog(route = Page.RemoveNotebook.route) {
                        DeleteNotebookDialog(
                            viewModel = getNavComposeViewModel()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun getComposeViewModelOwner(): ViewModelOwner {
    return ViewModelOwner.from(
        LocalViewModelStoreOwner.current!!,
        LocalSavedStateRegistryOwner.current
    )
}

@Composable
private inline fun <reified T : ViewModel> getNavComposeViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val viewModelOwner = getComposeViewModelOwner()
    return getKoin().getViewModel(qualifier, { viewModelOwner }, parameters)
}