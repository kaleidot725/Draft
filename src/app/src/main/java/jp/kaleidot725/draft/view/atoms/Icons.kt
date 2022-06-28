package jp.kaleidot725.draft.view.atoms

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckCircle
import compose.icons.feathericons.Copy
import compose.icons.feathericons.Menu
import compose.icons.feathericons.Trash
import compose.icons.feathericons.X

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

@Composable
fun CopyIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = FeatherIcons.Copy,
        contentDescription = "Copy",
        modifier = modifier
    )
}

@Composable
fun TrashIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = FeatherIcons.Trash,
        contentDescription = "Trash",
        modifier = modifier
    )
}

@Composable
fun CloseIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = FeatherIcons.X,
        contentDescription = "Close",
        modifier = modifier
    )
}