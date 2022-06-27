package jp.kaleidot725.draft.view.atoms

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckCircle
import compose.icons.feathericons.Menu

@Composable
fun MenuIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = FeatherIcons.Menu,
        contentDescription = "Menu",
        modifier = modifier
    )
}

@Composable
fun CheckCircleIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = FeatherIcons.CheckCircle,
        contentDescription = "CheckCircle",
        modifier = modifier
    )
}
