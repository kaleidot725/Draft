package jp.kaleidot725.emomemo.view.pages.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit2
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.molecules.ActionButton
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
    onNavigateDeleteNotebook: (Long) -> Unit,
    onNavigateAddMemo: (Long) -> Unit,
    onNavigateMemoDetails: (Long) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                MainSideEffect.NavigateAddNotebook -> onNavigateAddNotebook()
                is MainSideEffect.NavigateDeleteNotebook -> onNavigateDeleteNotebook(it.notebookId)
                is MainSideEffect.NavigateAddMemo -> onNavigateAddMemo(it.notebookId)
                is MainSideEffect.NavigateMemoDetails -> onNavigateMemoDetails(it.memoId)
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
                content = { paddingValues ->
                    Crossfade(
                        targetState = uiState.result,
                        animationSpec = tween(600),
                        modifier = Modifier.padding(paddingValues)
                    ) { state ->
                        when (state) {
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
                    ActionButton(
                        onClick = { viewModel.createMemo() },
                        iconVector = FeatherIcons.Edit2,
                        iconDescription = "Create memo",
                        text = stringResource(id = R.string.add_memo_action)
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
