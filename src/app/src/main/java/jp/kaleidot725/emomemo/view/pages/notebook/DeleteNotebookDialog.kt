package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.molecules.OkAndCancelButtons
import jp.kaleidot725.emomemo.view.organisms.dropdown.NotebookDropdownMenu
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DeleteNotebookDialog(
    viewModel: DeleteNotebookViewModel,
    onClose: () -> Unit
) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                DeleteNotebookSideEffect.Close -> onClose.invoke()
            }
        }
    }

    Surface(shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(16.dp)
        ) {
            Texts.TitleLarge(text = stringResource(id = R.string.delete_notebook_title))

            Spacer(modifier = Modifier.height(16.dp))

            NotebookDropdownMenu(
                label = stringResource(id = R.string.delete_notebook_field_title),
                notebooks = uiState.notebooks,
                selectedNotebook = uiState.selectedNotebook,
                onSelect = { viewModel.select(it) },
                modifier = Modifier.wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OkAndCancelButtons(
                okText = stringResource(id = R.string.delete_notebook_ok),
                onOk = { viewModel.ok() },
                enabledOk = uiState.canDelete,
                cancelText = stringResource(id = R.string.delete_notebook_cancel),
                onCancel = { viewModel.cancel() },
                enabledCancel = true,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            )
        }
    }
}