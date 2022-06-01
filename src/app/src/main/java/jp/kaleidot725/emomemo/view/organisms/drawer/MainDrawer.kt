package jp.kaleidot725.emomemo.view.organisms.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.sample.SampleData
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    selectedNotebook: NotebookEntity?,
    notebooks: List<NotebookEntity>,
    onAddNotebook: () -> Unit,
    onClickNotebook: (NotebookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Texts.TitleLarge(
                text = stringResource(id = R.string.top_title).uppercase(Locale.ENGLISH),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        item {
            Divider(
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
            )
        }

        items(notebooks) {
            NavigationDrawerItem(
                label = { Texts.TitleMedium(text = it.title) },
                selected = it == selectedNotebook,
                onClick = { onClickNotebook(it) }
            )
        }

        item {
            OutlinedButton(
                onClick = { onAddNotebook.invoke() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.add_notebook_title))
            }
        }
    }
}

@Preview
@Composable
private fun MainDrawer_Preview() {
    MainDrawer(selectedNotebook = SampleData.notebookList.first(), notebooks = SampleData.notebookList, onAddNotebook = {}, onClickNotebook = {})
}
