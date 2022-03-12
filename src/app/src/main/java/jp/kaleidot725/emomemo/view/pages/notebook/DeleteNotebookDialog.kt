package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DeleteNotebookDialog(viewModel: DeleteNotebookViewModel) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    Text(text = uiState.test)
}