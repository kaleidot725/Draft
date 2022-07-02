package jp.kaleidot725.draft.view.organisms.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.draft.view.atoms.CancelIcon
import jp.kaleidot725.draft.view.atoms.CopyIcon
import jp.kaleidot725.draft.view.atoms.TrashIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorMenus(
    onCopy: () -> Unit = {},
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            EditorMenu(
                icon = { CopyIcon() },
                label = { Text(text = "Copy") },
                modifier = Modifier
                    .weight(0.2f)
                    .align(Alignment.CenterVertically)
                    .clickable { onCopy() }
            )
            EditorMenu(
                icon = { TrashIcon() },
                label = { Text(text = "Delete") },
                modifier = Modifier
                    .weight(0.2f)
                    .align(Alignment.CenterVertically)
                    .clickable { onDelete() }
            )
            EditorMenu(
                icon = { CancelIcon() },
                label = { Text(text = "Cancel") },
                modifier = Modifier
                    .weight(0.2f)
                    .align(Alignment.CenterVertically)
                    .clickable { onCancel() }
            )
        }
    }
}

@Preview
@Composable
private fun EditorMenus_Preview() {
    EditorMenus()
}

