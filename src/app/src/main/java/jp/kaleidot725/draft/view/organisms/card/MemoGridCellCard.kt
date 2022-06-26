package jp.kaleidot725.draft.view.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.view.atoms.Texts
import jp.kaleidot725.draft.view.sample.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridMemoCard(
    memo: MemoEntity,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Texts.TitleMedium(text = memo.title, maxLines = 1, modifier = Modifier.fillMaxWidth())

            Divider(color = Color.LightGray)

            Texts.TitleMedium(text = memo.content, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun MemoCard_Preview() {
    GridMemoCard(
        memo = SampleData.memoList[0],
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
    )
}
