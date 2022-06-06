package jp.kaleidot725.emomemo.view.pages.memo.bottom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun MemoBottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Texts.BodyMedium(text = "TEST")
    }
}