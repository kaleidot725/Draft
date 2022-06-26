package jp.kaleidot725.draft.view.organisms.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.view.organisms.GridMemoCard
import jp.kaleidot725.draft.view.sample.SampleData

@Composable
fun MemoList(
    memos: List<MemoEntity>,
    onClickMemo: (MemoEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = memos, key = { item -> item.id }) { memo ->
            GridMemoCard(
                memo = memo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clickable { onClickMemo(memo) }
            )
        }
    }
}

@Preview
@Composable
private fun MemoList_Preview() {
    MemoList(memos = SampleData.memoList, onClickMemo = {}, modifier = Modifier.fillMaxSize())
}
