package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import jp.kaleidot725.emomemo.view.templates.memo.MemoTemplate

@Composable
fun DeleteNotebookDialog(viewModel: DeleteNotebookViewModel) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    MemoTemplate(
        topBar = {},
        content = {
            Text(text = uiState.test)
        }
    )
}