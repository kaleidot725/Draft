package jp.kaleidot725.emomemo.view.pages.memo.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.view.atoms.BasicTextFields
import jp.kaleidot725.emomemo.view.organisms.topbar.MemoTopBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailPage(viewModel: MemoDetailViewModel, onBack: () -> Unit, onDeleteMemo: (memoId: Int) -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                MemoDetailSideEffect.Back -> onBack.invoke()
                is MemoDetailSideEffect.DeleteMemo -> onDeleteMemo.invoke(it.memoId)
            }
        }
    }

    Scaffold(
        topBar = {
            MemoTopBar(
                title = uiState.memoEntity?.title ?: "",
                onChangeTitle = { viewModel.updateTitle(it) },
                onClickNavigationIcon = { viewModel.back() },
                onDeleteMemo = { viewModel.deleteMemo() },
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsWithImePadding(),
            ) {
                BasicTextFields.BodyLarge(
                    text = uiState.memoEntity?.content ?: "",
                    onValueChange = { viewModel.updateContent(it) },
                    singleLine = false,
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        modifier = Modifier.systemBarsPadding()
    )
}
