package jp.kaleidot725.emomemo.view.organisms.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.sample.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    selectedNotebook: NotebookEntity?,
    notebooks: List<NotebookEntity>,
    onAddNotebook: () -> Unit,
    onClickNotebook: (NotebookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Texts.DisplaySmall(
            text = stringResource(id = R.string.top_title), modifier = Modifier.fillMaxWidth()
        )
        
        Divider()

        Box(modifier = Modifier.fillMaxWidth()) {
            Texts.TitleMedium(
                text = stringResource(id = R.string.navigation_drawer_notebook_title),
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { onAddNotebook.invoke() }
                    .align(Alignment.CenterEnd)
            )
        }

        if (notebooks.isNotEmpty()) {
            notebooks.forEach {
                NavigationDrawerItem(label = { Texts.TitleMedium(text = it.title) },
                    selected = it == selectedNotebook,
                    onClick = { onClickNotebook(it) })
            }
        }
    }
}

@Preview
@Composable
private fun MainDrawer_Preview() {
    MainDrawer(selectedNotebook = SampleData.notebookList.first(), notebooks = SampleData.notebookList, onAddNotebook = {}, onClickNotebook = {})
}
