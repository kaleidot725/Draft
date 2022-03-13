package jp.kaleidot725.emomemo.view.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun OkAndCancelButtons(
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