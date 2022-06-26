package jp.kaleidot725.draft.view.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    onClick: () -> Unit,
    iconVector: ImageVector,
    iconDescription: String,
    text: String,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(onClick = { onClick() }, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(iconVector, iconDescription, Modifier.align(Alignment.CenterVertically))
            Text(text = text, Modifier.align(Alignment.CenterVertically))
        }
    }
}

@Preview
@Composable
private fun ActionButton_Preview() {
    ActionButton(onClick = {}, iconVector = Icons.Filled.Edit, iconDescription = "Add", text = "SAMPLE")
}
