package jp.kaleidot725.emomemo.view.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.view.molecules.Titles
import jp.kaleidot725.emomemo.view.sample.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoCard(
    memo: MemoEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Titles(subtitle1 = memo.title, subtitle2 = memo.content, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
private fun MemoCard_Preview() {
    MemoCard(
        memo = SampleData.memoList[0],
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}