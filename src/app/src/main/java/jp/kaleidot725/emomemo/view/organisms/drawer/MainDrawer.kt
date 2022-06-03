package jp.kaleidot725.emomemo.view.organisms.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Award
import compose.icons.feathericons.Edit2
import compose.icons.feathericons.FileText
import compose.icons.feathericons.Phone
import compose.icons.feathericons.Settings
import compose.icons.feathericons.Trash
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
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Texts.TitleMedium(
                    text = stringResource(id = R.string.notebook),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
            }

            items(notebooks) {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = it.title) },
                    selected = it == selectedNotebook,
                    onClick = { onClickNotebook(it) },
                    icon = { Icon(imageVector = FeatherIcons.FileText, contentDescription = "notebook icon") }
                )
            }

            item {
                Divider()
            }

            item {
                Texts.TitleMedium(
                    text = stringResource(id = R.string.operation),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = stringResource(id = R.string.add_notebook_action)) },
                    selected = false,
                    onClick = { onAddNotebook.invoke() },
                    icon = { Icon(imageVector = FeatherIcons.Edit2, contentDescription = "add notebook icon") }
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = stringResource(id = R.string.delete_notebook_action)) },
                    selected = false,
                    onClick = { /** TODO */ },
                    icon = { Icon(imageVector = FeatherIcons.Trash, contentDescription = "delete notebook icon") }
                )
            }

            item {
                Divider()
            }

            item {
                Texts.TitleMedium(
                    text = stringResource(id = R.string.other),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = stringResource(id = R.string.setting)) },
                    selected = false,
                    onClick = { /** TODO */ },
                    icon = { Icon(imageVector = FeatherIcons.Settings, contentDescription = "settings icon") }
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = stringResource(id = R.string.license)) },
                    selected = false,
                    onClick = { /** TODO */ },
                    icon = { Icon(imageVector = FeatherIcons.Award, contentDescription = "license icon") }
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = stringResource(id = R.string.contact)) },
                    selected = false,
                    onClick = { /** TODO */ },
                    icon = { Icon(imageVector = FeatherIcons.Phone, contentDescription = "contact icon") }
                )
            }

            item {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun MainDrawer_Preview() {
    MainDrawer(selectedNotebook = SampleData.notebookList.first(), notebooks = SampleData.notebookList, onAddNotebook = {}, onClickNotebook = {})
}
