package jp.kaleidot725.emomemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import jp.kaleidot725.emomemo.ext.animationComposable
import jp.kaleidot725.emomemo.ext.getNavComposeViewModel
import jp.kaleidot725.emomemo.view.pages.Page
import jp.kaleidot725.emomemo.view.pages.main.MainPage
import jp.kaleidot725.emomemo.view.pages.memo.add.AddMemoDialog
import jp.kaleidot725.emomemo.view.pages.memo.delete.DeleteMemoDialog
import jp.kaleidot725.emomemo.view.pages.memo.detail.MemoDetailPage
import jp.kaleidot725.emomemo.view.pages.notebook.add.AddNotebookDialog
import jp.kaleidot725.emomemo.view.pages.notebook.delete.DeleteNotebookDialog
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                ProvideWindowInsets {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(navController = navController, startDestination = Page.Main.route) {
                        addMainPage(navController)
                        addAddNotebookPage(navController)
                        addDeleteNotebookDialog(navController)
                        addAddMemoPage(navController)
                        addMemoPage(navController)
                        addDeleteMemoDialog(navController)
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.addMainPage(navController: NavController) {
    animationComposable(route = Page.Main.route) {
        MainPage(
            viewModel = getNavComposeViewModel(),
            onNavigateAddNotebook = { navController.navigate(Page.AddNoteBook.route) },
            onNavigateDeleteNotebook = { navController.navigate(Page.DeleteNotebook.createRoute(it)) },
            onNavigateAddMemo = { navController.navigate(Page.AddMemo.createRoute(it)) },
            onNavigateMemoDetails = { navController.navigate(Page.Memo.createRoute(it)) }
        )
    }
}

private fun NavGraphBuilder.addAddNotebookPage(navController: NavController) {
    dialog(route = Page.AddNoteBook.route) {
        AddNotebookDialog(
            viewModel = getNavComposeViewModel(),
            onClose = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.addDeleteNotebookDialog(navController: NavController) {
    dialog(route = Page.DeleteNotebook.route) {
        DeleteNotebookDialog(
            viewModel = getNavComposeViewModel { parametersOf(Page.DeleteNotebook.getArgumentId(it)) },
            onBackHome = { navController.popBackStack(Page.Main.route, inclusive = false) },
            onClose = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.addAddMemoPage(navController: NavController) {
    dialog(route = Page.AddMemo.route) {
        AddMemoDialog(
            viewModel = getNavComposeViewModel { parametersOf(Page.AddMemo.getArgumentId(it)) },
            onNavigateMemo = { navController.navigate(Page.Memo.createRoute(it)) },
            onClose = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.addMemoPage(navController: NavController) {
    animationComposable(route = Page.Memo.route) {
        MemoDetailPage(
            viewModel = getNavComposeViewModel { parametersOf(Page.Memo.getArgumentId(it)) },
            onBack = { navController.popBackStack() },
            onDeleteMemo = { navController.navigate(Page.DeleteMemo.createRoute(it)) }
        )
    }
}

private fun NavGraphBuilder.addDeleteMemoDialog(navController: NavController) {
    dialog(route = Page.DeleteMemo.route) {
        DeleteMemoDialog(
            viewModel = getNavComposeViewModel { parametersOf(Page.DeleteMemo.getArgumentId(it)) },
            onBackHome = { navController.popBackStack(Page.Main.route, inclusive = false) },
            onClose = { navController.popBackStack() }
        )
    }
}