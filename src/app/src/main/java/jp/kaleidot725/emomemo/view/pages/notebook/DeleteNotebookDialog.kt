package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DeleteNotebookDialog(
    viewModel: DeleteNotebookViewModel,
    onBackHome: () -> Unit,
    onClose: () -> Unit
) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                DeleteNotebookSideEffect.BackHome -> onBackHome.invoke()
                DeleteNotebookSideEffect.Close -> onClose.invoke()
            }
        }
    }

    Surface(shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Texts.TitleLarge(text = stringResource(id = R.string.delete_notebook_title))

            Texts.BodyMedium(text = stringResource(id = R.string.delete_notebook_message, uiState.notebook?.title ?: ""), maxLines = 3)

            OkAndCancelButtons(
                okText = stringResource(id = R.string.delete_notebook_ok),
                onOk = { viewModel.ok() },
                enabledOk = true,
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
