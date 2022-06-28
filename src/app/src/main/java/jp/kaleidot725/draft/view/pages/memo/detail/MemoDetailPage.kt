package jp.kaleidot725.draft.view.pages.memo.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import jp.kaleidot725.draft.ext.clickableNoRipple
import jp.kaleidot725.draft.view.atoms.CloseIcon
import jp.kaleidot725.draft.view.atoms.CopyIcon
import jp.kaleidot725.draft.view.atoms.TrashIcon
import jp.kaleidot725.draft.view.organisms.editor.EditorMenu
import jp.kaleidot725.draft.view.organisms.editor.FieldIcon
import jp.kaleidot725.draft.view.organisms.topbar.MemoTopBar
import jp.kaleidot725.texteditor.extension.rememberTextEditorState
import jp.kaleidot725.texteditor.view.TextEditor
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MemoDetailPage(viewModel: MemoDetailViewModel, onBack: () -> Unit, onNavigateMemoBottomSheet: (memoId: Long) -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                MemoDetailSideEffect.Back -> onBack.invoke()
                is MemoDetailSideEffect.DeleteMemo -> onNavigateMemoBottomSheet.invoke(it.memoId)
            }
        }
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }

    Scaffold(
        topBar = {
            Box {
                MemoTopBar(
                    title = uiState.memoEntity?.title ?: "",
                    onClickNavigationIcon = {
                        viewModel.back()
                    },
                    onDeleteMemo = { viewModel.deleteMemo() },
                )

                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        },
        content = { contentPadding ->
            if (uiState.memoEntity != null) {
                val editorState by rememberTextEditorState(text = uiState.memoEntity?.content ?: "")
                val isMultipleSelectionMode by editorState.isMultipleSelectionMode

                Box {
                    TextEditor(
                        textEditorState = editorState,
                        onUpdatedState = { viewModel.saveMemo(editorState.getAllText()) },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) { _, isSelected, innerTextField ->
                        Row(modifier = Modifier
                            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.White)
                            .clickableNoRipple {
                                if (!isMultipleSelectionMode && isSelected) {
                                    editorState.enableMultipleSelectionMode(true)
                                    keyboardController?.hide()
                                }
                            }
                        ) {
                            innerTextField(
                                modifier = Modifier
                                    .weight(0.9f)
                                    .align(Alignment.CenterVertically)
                            )
                            FieldIcon(
                                isMultipleSelection = isMultipleSelectionMode,
                                isSelected = isSelected,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(4.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }

                    if (isMultipleSelectionMode) {
                        ElevatedCard(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .height(80.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {
                                EditorMenu(
                                    icon = { CopyIcon() },
                                    label = { Text(text = "Copy") },
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            clipboardManager.setText(AnnotatedString(editorState.getSelectedText()))
                                            Toast
                                                .makeText(context, "Copy text to clipboard", Toast.LENGTH_SHORT)
                                                .show()
                                            editorState.enableMultipleSelectionMode(false)
                                        }
                                )
                                EditorMenu(
                                    icon = { TrashIcon() },
                                    label = { Text(text = "Delete") },
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            editorState.deleteSelectedLines()
                                            editorState.enableMultipleSelectionMode(false)
                                        }
                                )
                                EditorMenu(
                                    icon = { CloseIcon() },
                                    label = { Text(text = "Cancel") },
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            editorState.enableMultipleSelectionMode(false)
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
