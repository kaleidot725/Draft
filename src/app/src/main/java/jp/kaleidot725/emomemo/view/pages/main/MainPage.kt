package jp.kaleidot725.emomemo.view.pages.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.molecules.FloatingActionIconButton
import jp.kaleidot725.emomemo.view.organisms.list.MemoList
import jp.kaleidot725.emomemo.view.organisms.topbar.MainTopAppBar
import jp.kaleidot725.emomemo.view.sample.SampleData
import jp.kaleidot725.emomemo.view.templates.main.MainTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val pinnedScrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    MainTemplate(
        topBar = { MainTopAppBar(title = "MAIN TOP APP BAR", scrollBehavior = pinnedScrollBehavior) },
        mainContent = {
            Box {
                MemoList(memos = SampleData.memoDetailsList, modifier = Modifier.padding(8.dp))
                FloatingActionIconButton(
                    onClick = {},
                    iconVector = Icons.Filled.Edit,
                    iconDescription = "Add Memo",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
private fun MainPage_Preview() {
    MainPage()
}