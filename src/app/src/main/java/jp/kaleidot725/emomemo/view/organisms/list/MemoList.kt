package jp.kaleidot725.emomemo.view.organisms.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.domain.usecase.MemoDetails
import jp.kaleidot725.emomemo.view.organisms.MemoCard
import jp.kaleidot725.emomemo.view.sample.SampleData

@Composable
fun MemoList(
    memos: List<MemoDetails>,
    onClickMemo: (MemoDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(memos) { memo ->
            MemoCard(
                memo = memo.memo,
                lastMessage = memo.lastMessage,
                messageCount = memo.messageCount,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable { onClickMemo(memo) }
            )
        }
    }
}

@Preview
@Composable
private fun MemoList_Preview() {
    MemoList(memos = SampleData.memoDetailsList, onClickMemo = {}, modifier = Modifier.fillMaxSize())
}
