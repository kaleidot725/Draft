package jp.kaleidot725.emomemo.view.organisms.drawer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
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
    selectedNotebook: NotebookEntity,
    notebooks: List<NotebookEntity>,
    onAddNotebook: () -> Unit,
    onDeleteNotebook: () -> Unit,
    onClickNotebook: (NotebookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Texts.TitleLarge(
                text = stringResource(id = R.string.top_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        item {
            Texts.TitleMedium(
                text = stringResource(id = R.string.navigation_drawer_sub_menu_action),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }

        item {
            NavigationDrawerItem(
                label = { Texts.TitleMedium(text = stringResource(id = R.string.navigation_drawer_item_add_notebook)) },
                selected = false,
                onClick = { onAddNotebook() }
            )
        }

        item {
            NavigationDrawerItem(
                label = { Texts.TitleMedium(text = stringResource(id = R.string.navigation_drawer_item_remove_notebook)) },
                selected = false,
                onClick = { onDeleteNotebook() }
            )
        }

        item {
            Divider(modifier = Modifier.padding(16.dp))
        }

        item {
            Texts.TitleMedium(
                text = stringResource(id = R.string.navigation_drawer_sub_menu_notebooks),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        items(notebooks) {
            NavigationDrawerItem(label = { Texts.TitleMedium(text = it.title) }, selected = it == selectedNotebook, onClick = { onClickNotebook(it) })
        }
    }
}

@Preview
@Composable
private fun MainDrawer_Preview() {
    MainDrawer(
        selectedNotebook = SampleData.notebookList.first(),
        notebooks = SampleData.notebookList,
        onAddNotebook = {},
        onDeleteNotebook = {},
        onClickNotebook = {}
    )
}
