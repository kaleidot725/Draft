package jp.kaleidot725.draft.view.organisms.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.draft.view.atoms.CheckCircleIcon
import jp.kaleidot725.draft.view.atoms.MenuIcon

@Composable
fun TextFieldActionIcon(
    isMultipleSelection: Boolean,
    isSelected: Boolean,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        when {
            isMultipleSelection && isSelected -> {
                CheckCircleIcon()
            }
            isSelected -> {
                MenuIcon()
            }
        }
    }
}