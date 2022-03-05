package jp.kaleidot725.emomemo.view.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun Message(message: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Texts.TitleMedium(
            text = message,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp)
        )
    }
}

@Preview
@Composable
private fun TitlesAndCount_Preview() {
    Message(
        message = "AATTBBDDAATTBBDDAATTBBDDAATTBBDDAATTBBDDAATTBBDDAATTBBDD", modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}