package jp.kaleidot725.emomemo.view.molecules

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingActionIconButton(onClick: () -> Unit, iconVector: ImageVector, iconDescription: String, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = { onClick() }, modifier = modifier) {
    Icon(iconVector, iconDescription)
}
}

@Preview
@Composable
private fun FloatingIconButton_Preview() {
    FloatingActionIconButton(onClick = {}, iconVector = Icons.Filled.Edit, iconDescription = "Add")
}
