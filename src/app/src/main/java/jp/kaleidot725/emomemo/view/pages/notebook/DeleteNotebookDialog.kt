package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.molecules.OkAndCancelButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteNotebookDialog(viewModel: DeleteNotebookViewModel) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    var text by remember { mutableStateOf("TEXT") }

    Surface(shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(16.dp)
        ) {
            Texts.TitleLarge(text = "ノートブックを削除する")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = text, onValueChange = { text = it })

            Spacer(modifier = Modifier.height(8.dp))

            OkAndCancelButtons(
                okText = "削除する",
                onOk = {},
                enabledOk = false,
                cancelText = "キャンセル",
                onCancel = {},
                enabledCancel = false,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            )
        }
    }
}