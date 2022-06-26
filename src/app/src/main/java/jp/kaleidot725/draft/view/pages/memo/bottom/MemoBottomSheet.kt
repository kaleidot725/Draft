package jp.kaleidot725.draft.view.pages.memo.bottom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit
import compose.icons.feathericons.Trash2
import jp.kaleidot725.draft.view.atoms.Texts
import jp.kaleidot725.draft.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemoBottomSheet(
    viewModel: MemoBottomSheetViewModel,
    onEditMemo: (Long) -> Unit,
    onDeleteNotebook: (Long) -> Unit
) {
    val state by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is MemoBottomSheetSideEffect.NavigateEditMemo -> onEditMemo(it.memoId)
                is MemoBottomSheetSideEffect.NavigateDeleteMemo -> onDeleteNotebook(it.memoId)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .navigationBarsPadding()
            .padding(4.dp)
    ) {
        Texts.TitleMedium(text = state.memo?.title ?: "", modifier = Modifier.padding(12.dp))

        ListItem(
            icon = { Icon(imageVector = FeatherIcons.Edit, contentDescription = "edit notebook") },
            modifier = Modifier.clickable { viewModel.editMemo() }
        ) {
            Texts.TitleMedium(
                text = stringResource(id = R.string.memo_bottom_edit)
            )
        }

        ListItem(
            icon = { Icon(imageVector = FeatherIcons.Trash2, contentDescription = "delete notebook") },
            modifier = Modifier.clickable { viewModel.deleteMemo() }
        ) {
            Texts.TitleMedium(
                text = stringResource(id = R.string.memo_bottom_delete)
            )
        }
    }
}