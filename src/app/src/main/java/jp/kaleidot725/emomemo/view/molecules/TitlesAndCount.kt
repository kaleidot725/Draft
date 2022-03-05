package jp.kaleidot725.emomemo.view.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun TitlesAndCount(subtitle1: String, subtitle2: String, count: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterStart)
                .padding(horizontal = 12.dp)
                .padding(end = 100.dp)
        ) {
            Texts.TitleMedium(text = subtitle1, maxLines = 1)
            Texts.TitleMedium(text = subtitle2, maxLines = 1)
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .align(Alignment.CenterEnd)
        ) {
            Texts.TitleLarge(
                text = count,
                maxLines = 1,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun TitlesAndCount_Preview() {
    TitlesAndCount(
        subtitle1 = "買い物リスト買い物リスト買い物リスト買い物リスト",
        subtitle2 = "明日までに買い物するもの明日までに買い物するもの明日までに買い物するもの明日までに買い物するもの",
        count = "100",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}