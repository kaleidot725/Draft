package jp.kaleidot725.emomemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import jp.kaleidot725.emomemo.ext.animationComposable
import jp.kaleidot725.emomemo.ext.getNavComposeViewModel
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.pages.Destination
import jp.kaleidot725.emomemo.view.pages.main.MainPage
import jp.kaleidot725.emomemo.view.pages.memo.add.AddMemoDialog
import jp.kaleidot725.emomemo.view.pages.memo.delete.DeleteMemoDialog
import jp.kaleidot725.emomemo.view.pages.memo.detail.MemoDetailPage
import jp.kaleidot725.emomemo.view.pages.notebook.add.AddNotebookDialog
import jp.kaleidot725.emomemo.view.pages.notebook.delete.DeleteNotebookDialog
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
class ComposeMainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                ProvideWindowInsets {
                    val bottomSheetNavigator = rememberBottomSheetNavigator()
                    val navController = rememberAnimatedNavController(bottomSheetNavigator)
                    ModalBottomSheetLayout(bottomSheetNavigator) {
                        AnimatedNavHost(navController = navController, startDestination = Destination.Main.route) {
                            addMainPage(navController)
                            addNotebookBottomSheet(navController)
                            addAddNotebookPage(navController)
                            addDeleteNotebookDialog(navController)
                            addMemoBottomSheet(navController)
                            addAddMemoPage(navController)
                            addMemoPage(navController)
                            addDeleteMemoDialog(navController)
                        }
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.addMainPage(navController: NavController) {
    animationComposable(route = Destination.Main.route) {
        MainPage(
            viewModel = getNavComposeViewModel(),
            onNavigateAddNotebook = { navController.navigate(Destination.AddNoteBook.route) },
            onNavigateNotebookBottomSheet = { navController.navigate(Destination.MemoBottom.route) },
            onNavigateAddMemo = { navController.navigate(Destination.AddMemo.createRoute(it)) },
            onNavigateMemoDetails = { navController.navigate(Destination.Memo.createRoute(it)) }
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.addNotebookBottomSheet(navController: NavController) {
    bottomSheet(route = Destination.NotebookBottom.route) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Texts.BodyMedium(text = "TEST")
        }
    }
}

private fun NavGraphBuilder.addAddNotebookPage(navController: NavController) {
    dialog(route = Destination.AddNoteBook.route) {
        AddNotebookDialog(
            viewModel = getNavComposeViewModel(),
            onClose = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.addDeleteNotebookDialog(navController: NavController) {
    dialog(route = Destination.DeleteNotebook.route) {
        DeleteNotebookDialog(
            viewModel = getNavComposeViewModel { parametersOf(Destination.DeleteNotebook.getArgumentId(it)) },
            onBackHome = { navController.popBackStack(Destination.Main.route, inclusive = false) },
            onClose = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.addMemoBottomSheet(navController: NavController) {
    bottomSheet(route = Destination.MemoBottom.route) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Texts.BodyMedium(text = "TEST")
        }
    }
}

private fun NavGraphBuilder.addAddMemoPage(navController: NavController) {
    dialog(route = Destination.AddMemo.route) {
        AddMemoDialog(
            viewModel = getNavComposeViewModel { parametersOf(Destination.AddMemo.getArgumentId(it)) },
            onNavigateMemo = { navController.navigate(Destination.Memo.createRoute(it)) },
            onClose = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.addMemoPage(navController: NavController) {
    animationComposable(route = Destination.Memo.route) {
        MemoDetailPage(
            viewModel = getNavComposeViewModel { parametersOf(Destination.Memo.getArgumentId(it)) },
            onBack = { navController.popBackStack() },
            onNavigateMemoBottomSheet = { navController.navigate(Destination.MemoBottom.route) }
        )
    }
}

private fun NavGraphBuilder.addDeleteMemoDialog(navController: NavController) {
    dialog(route = Destination.DeleteMemo.route) {
        DeleteMemoDialog(
            viewModel = getNavComposeViewModel { parametersOf(Destination.DeleteMemo.getArgumentId(it)) },
            onBackHome = { navController.popBackStack(Destination.Main.route, inclusive = false) },
            onClose = { navController.popBackStack() }
        )
    }
}