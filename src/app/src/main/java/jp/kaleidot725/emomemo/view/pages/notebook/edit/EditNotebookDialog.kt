package jp.kaleidot725.emomemo.view.pages.notebook.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.molecules.OkAndCancelButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditNotebookDialog(viewModel: EditNotebookViewModel, onClose: () -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                EditNotebookSideEffect.Close -> onClose()
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
        ) {
            Texts.HeadlineSmall(text = stringResource(id = R.string.rename_notebook_title))

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.notebookTitle,
                onValueChange = { viewModel.updateNotebookTitle(it) },
                singleLine = true,
                label = {
                    Texts.BodyMedium(text = stringResource(id = R.string.rename_notebook_field_title))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OkAndCancelButton(
                okText = stringResource(id = R.string.rename_notebook_ok),
                onOk = { viewModel.ok() },
                enabledOk = uiState.canRename,
                cancelText = stringResource(id = R.string.rename_notebook_cancel),
                enabledCancel = true,
                onCancel = { viewModel.cancel() },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            )
        }
    }
}