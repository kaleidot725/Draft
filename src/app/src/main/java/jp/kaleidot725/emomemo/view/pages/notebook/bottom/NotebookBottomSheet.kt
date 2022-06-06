package jp.kaleidot725.emomemo.view.pages.notebook.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.view.atoms.Texts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotebookBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .systemBarsPadding()
    ) {
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
        ListItem { Texts.TitleLarge(text = "TEST") }
    }
}