package jp.kaleidot725.emomemo.view.pages.notebook.bottom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import jp.kaleidot725.emomemo.view.atoms.Texts
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotebookBottomSheet(
    viewModel: NotebookBottomSheetViewModel,
    onEditNotebook: (Long) -> Unit,
    onDeleteNotebook: (Long) -> Unit
) {
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is NotebookBottomSheetSideEffect.NavigateDeleteNotebook -> onDeleteNotebook(it.notebookId)
                is NotebookBottomSheetSideEffect.NavigateEditNotebook -> onEditNotebook(it.notebookId)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .navigationBarsPadding()
    ) {
        ListItem(modifier = Modifier.clickable { viewModel.navigateEditNotebook() }) {
            Texts.TitleLarge(text = "Edit XXX notebook")
        }

        ListItem(modifier = Modifier.clickable { viewModel.navigateDeleteNotebook() }) {
            Texts.TitleLarge(text = "Delete XXX notebook")
        }
    }
}