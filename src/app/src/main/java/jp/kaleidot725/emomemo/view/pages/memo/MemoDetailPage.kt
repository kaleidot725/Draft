package jp.kaleidot725.emomemo.view.pages.memo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import jp.kaleidot725.emomemo.view.atoms.TextFields
import jp.kaleidot725.emomemo.view.organisms.topbar.MemoTopBar
import jp.kaleidot725.emomemo.view.templates.memo.MemoTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailPage(viewModel: MemoDetailViewModel) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    var title by remember { mutableStateOf("お買い物") }
    var content by remember { mutableStateOf("TEST TEST TEST TEST") }

    MemoTemplate(
        topBar = {
            MemoTopBar(
                title = title,
                onTitleChange = { title = it },
                modifier = Modifier,
                scrollBehavior = scrollBehavior
            )
        },
        content = {
            TextFields.BodyLarge(text = content, onValueChange = { content = it }, singleLine = false, modifier = Modifier.fillMaxSize())
        }
    )
}