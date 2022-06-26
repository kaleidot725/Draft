package jp.kaleidot725.draft.view.pages.memo.edit

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
import jp.kaleidot725.draft.view.atoms.Texts
import jp.kaleidot725.draft.view.molecules.OkAndCancelButton
import jp.kaleidot725.draft.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditMemoDialog(
    viewModel: EditMemoViewModel,
    onClose: () -> Unit
) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                EditMemoSideEffect.Close -> onClose()
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
        ) {
            Texts.HeadlineSmall(text = stringResource(id = R.string.rename_memo_title))

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.memoTitle,
                onValueChange = { viewModel.updateMemoTitle(it) },
                singleLine = true,
                label = {
                    Texts.BodyMedium(text = stringResource(id = R.string.rename_memo_field_title))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OkAndCancelButton(
                okText = stringResource(id = R.string.rename_memo_ok),
                onOk = { viewModel.ok() },
                enabledOk = uiState.canRename,
                cancelText = stringResource(id = R.string.rename_memo_cancel),
                enabledCancel = true,
                onCancel = { viewModel.cancel() },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            )
        }
    }
}