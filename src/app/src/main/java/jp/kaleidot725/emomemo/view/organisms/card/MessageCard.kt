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
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.view.molecules.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageCard(message: MessageEntity, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(12.dp)
    ) {
        Message(message = message.value)
    }
}

@Preview
@Composable
private fun MessageCard_Preview() {
    MessageCard(
        message = MessageEntity(
            0, 0, 0, "MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE MESSAGE"
        ), modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}