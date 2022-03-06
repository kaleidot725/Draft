package jp.kaleidot725.emomemo.view.pages.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.molecules.FloatingActionIconButton
import jp.kaleidot725.emomemo.view.organisms.drawer.MainDrawer
import jp.kaleidot725.emomemo.view.organisms.list.MemoList
import jp.kaleidot725.emomemo.view.organisms.topbar.MainTopAppBar
import jp.kaleidot725.emomemo.view.templates.main.MainTemplate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: MainViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = remember { TopAppBarDefaults.enterAlwaysScrollBehavior() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect()
    }

    MainTemplate(
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
                modifier = Modifier.padding(8.dp)
            )
        },
        floatingAction = {
            FloatingActionIconButton(
                onClick = {},
                iconVector = Icons.Filled.Edit,
                iconDescription = "Add Memo"
            )
        },
        drawerState = drawerState,
        drawerContent = {
            MainDrawer(
                selectedNotebook = uiState.selectedNotebook,
                notebooks = uiState.notebooks,
                onAddNotebook = {
                    // TODO
                    coroutineScope.launch { drawerState.close() }
                },
                onDeleteNotebook = {
                    // TODO
                    coroutineScope.launch { drawerState.close() }
                },
                onClickNotebook = {
                    viewModel.selectNotebook(it)
                    coroutineScope.launch { drawerState.close() }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}

@Preview
@Composable
private fun MainPage_Preview() {
    MainPage(MainViewModel())
}