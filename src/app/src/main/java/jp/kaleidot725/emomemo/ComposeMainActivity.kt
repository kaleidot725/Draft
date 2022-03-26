package jp.kaleidot725.emomemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import jp.kaleidot725.emomemo.view.pages.Page
import jp.kaleidot725.emomemo.view.pages.main.MainPage
import jp.kaleidot725.emomemo.view.pages.memo.DeleteMemoDialog
import jp.kaleidot725.emomemo.view.pages.memo.MemoDetailPage
import jp.kaleidot725.emomemo.view.pages.notebook.AddNotebookDialog
import jp.kaleidot725.emomemo.view.pages.notebook.DeleteNotebookDialog
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Page.Main.route) {
                        composable(route = Page.Main.route) {
                            MainPage(viewModel = getNavComposeViewModel(),
                                onNavigateAddNotebook = { navController.navigate(Page.AddNoteBook.route) },
                                onNavigateDeleteNotebook = { navController.navigate(Page.DeleteNotebook.createRoute(it.id)) },
                                onNavigateMemoDetails = { navController.navigate(Page.Memo.createRoute(it.id)) })
                        }
                        dialog(route = Page.AddNoteBook.route) {
                            AddNotebookDialog(
                                viewModel = getNavComposeViewModel(),
                                onClose = { navController.popBackStack() }
                            )
                        }
                        dialog(route = Page.DeleteNotebook.route) {
                            DeleteNotebookDialog(
                                viewModel = getNavComposeViewModel { parametersOf(Page.DeleteNotebook.getArgumentId(it)) },
                                onBackHome = { navController.popBackStack(Page.Main.route, inclusive = false) },
                                onClose = { navController.popBackStack() }
                            )
                        }
                        composable(route = Page.Memo.route) {
                            MemoDetailPage(
                                viewModel = getNavComposeViewModel { parametersOf(Page.Memo.getArgumentId(it)) },
                                onBack = { navController.popBackStack() },
                                onDeleteMemo = { navController.navigate(Page.DeleteMemo.createRoute(it)) }
                            )
                        }
                        dialog(route = Page.DeleteMemo.route) {
                            DeleteMemoDialog(
                                viewModel = getNavComposeViewModel { parametersOf(Page.DeleteMemo.getArgumentId(it)) },
                                onBackHome = { navController.popBackStack(Page.Main.route, inclusive = false) },
                                onClose = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getComposeViewModelOwner(): ViewModelOwner {
    return ViewModelOwner.from(
        LocalViewModelStoreOwner.current!!, LocalSavedStateRegistryOwner.current
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