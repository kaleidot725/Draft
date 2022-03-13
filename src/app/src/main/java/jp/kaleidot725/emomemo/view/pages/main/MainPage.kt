package jp.kaleidot725.emomemo.view.pages.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.view.molecules.FloatingActionIconButton
import jp.kaleidot725.emomemo.view.organisms.drawer.MainDrawer
import jp.kaleidot725.emomemo.view.organisms.list.MemoList
import jp.kaleidot725.emomemo.view.organisms.topbar.MainTopAppBar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: MainViewModel,
    onNavigateAddNotebook: () -> Unit,
    onNavigateRemoveNotebook: () -> Unit,
    onNavigateMemoDetails: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = remember { TopAppBarDefaults.enterAlwaysScrollBehavior() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                MainSideEffect.NavigateAddNotebook -> onNavigateAddNotebook()
                MainSideEffect.NavigateRemoveNotebook -> onNavigateRemoveNotebook()
                MainSideEffect.NavigateMemoDetails -> onNavigateMemoDetails()
            }
        }
    }

    ProvideWindowInsets {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                MainDrawer(
                    selectedNotebook = uiState.selectedNotebook,
                    notebooks = uiState.notebooks,
                    onAddNotebook = {
                        viewModel.navigateAddNotebook()
                        coroutineScope.launch { drawerState.close() }
                    },
                    enabledDeleteNotebook = uiState.canDeleteNotebook,
                    onDeleteNotebook = {
                        viewModel.navigateRemoveNotebook()
                        coroutineScope.launch { drawerState.close() }
                    },
                    onClickNotebook = {
                        viewModel.selectNotebook(it)
                        coroutineScope.launch { drawerState.close() }
                    }
                )
            },
            content = {
                Scaffold(
                    topBar = {
                        MainTopAppBar(
                            title = uiState.selectedNotebook?.title ?: "",
                            scrollBehavior = scrollBehavior,
                            onClickNavigationIcon = { coroutineScope.launch { drawerState.open() } }
                        )
                    },
                    content = {
                        MemoList(
                            memos = uiState.memos,
                            onClickMemo = { viewModel.selectMemo(it) },
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    floatingActionButton = {
                        FloatingActionIconButton(
                            onClick = { viewModel.createMemo() },
                            iconVector = Icons.Filled.Edit,
                            iconDescription = "Add Memo"
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                )
            },
            modifier = Modifier.systemBarsPadding()
        )
    }
}
