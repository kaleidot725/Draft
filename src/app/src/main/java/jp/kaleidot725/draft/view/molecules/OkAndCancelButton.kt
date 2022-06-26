package jp.kaleidot725.draft.view.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.kaleidot725.draft.view.atoms.Texts

@Composable
fun OkAndCancelButton(
    okText: String,
    onOk: () -> Unit,
    enabledOk: Boolean,
    cancelText: String,
    onCancel: () -> Unit,
    enabledCancel: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        TextButton(onClick = onCancel, enabled = enabledCancel) {
            Texts.LabelLarge(text = cancelText)
        }

        TextButton(onClick = onOk, enabled = enabledOk) {
            Texts.LabelLarge(text = okText)
        }
    }
}

@Preview
@Composable
private fun OkAndCancelButton_Preview() {
    OkAndCancelButton(
        okText = "OK",
        onOk = {},
        enabledOk = false,
        cancelText = "Cancel",
        onCancel = {},
        enabledCancel = true
    )
}
