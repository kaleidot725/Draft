package jp.kaleidot725.emomemo.view.pages.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.view.atoms.Texts
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
    onNavigateDeleteNotebook: (NotebookEntity) -> Unit,
    onNavigateMemoDetails: (MemoEntity) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                MainSideEffect.NavigateAddNotebook -> onNavigateAddNotebook()
                is MainSideEffect.NavigateDeleteNotebook -> onNavigateDeleteNotebook(it.notebookEntity)
                is MainSideEffect.NavigateMemoDetails -> onNavigateMemoDetails(it.memoEntity)
            }
        }
    }

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
                onClickNotebook = {
                    viewModel.selectNotebook(it)
                    coroutineScope.launch { drawerState.close() }
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    Box {
                        MainTopAppBar(
                            title = uiState.selectedNotebook?.title ?: "",
                            onChangeTitle = { viewModel.updateSelectedNotebookTitle(it) },
                            enabledTitle = uiState.result != MainState.Result.NOT_FOUND_NOTEBOOK,
                            enabledAction = uiState.selectedNotebook != null,
                            scrollBehavior = scrollBehavior,
                            onClickNavigationIcon = { coroutineScope.launch { drawerState.open() } },
                            onDeleteNotebook = { viewModel.navigateDeleteNotebook() }
                        )

                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                        )
                    }
                },
                content = {
                    Box(modifier = Modifier.padding(it)) {
                        when (uiState.result) {
                            MainState.Result.SUCCESS -> {
                                MemoList(
                                    memos = uiState.memos,
                                    onClickMemo = { viewModel.selectMemo(it) },
                                )
                            }
                            MainState.Result.NOT_FOUND_NOTEBOOK -> {
                                Box(Modifier.fillMaxSize()) {
                                    Texts.BodyLarge(
                                        text = stringResource(id = R.string.main_not_found_notebook_message),
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                            MainState.Result.NOT_FOUND_MEMO -> {
                                Box(Modifier.fillMaxSize()) {
                                    Texts.BodyLarge(
                                        text = stringResource(id = R.string.main_not_found_memo_message),
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
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
