package jp.kaleidot725.emomemo.view.organisms

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
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.view.molecules.TitlesAndCount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoCard(memo: MemoEntity, lastMessage: MessageEntity, messageCount: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(12.dp)
    ) {
        TitlesAndCount(subtitle1 = memo.title, subtitle2 = lastMessage.value, count = messageCount.toString())
    }
}

@Preview
@Composable
private fun MemoCard_Preview() {
    MemoCard(
        memo = MemoEntity(0, 0, "MESSAGE"),
        lastMessage = MessageEntity(0, 0, 0, "LAST MESSAGE"),
        messageCount = 100,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}