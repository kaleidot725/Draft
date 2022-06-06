package jp.kaleidot725.emomemo.view.pages.memo.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.emomemo.view.atoms.Texts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemoBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .navigationBarsPadding()
    ) {
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
    }
}