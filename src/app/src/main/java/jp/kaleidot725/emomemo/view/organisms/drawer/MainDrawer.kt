package jp.kaleidot725.emomemo.view.organisms.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    enabledDeleteNotebook: Boolean,
    onDeleteNotebook: () -> Unit,
    onClickNotebook: (NotebookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Texts.TitleLarge(
            text = stringResource(id = R.string.top_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Texts.TitleMedium(
            text = stringResource(id = R.string.navigation_drawer_action_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        )

        NavigationDrawerItem(
            label = {
                Texts.TitleMedium(text = stringResource(id = R.string.navigation_drawer_create_notebook))
            },
            selected = false,
            onClick = { onAddNotebook() }
        )

        NavigationDrawerItem(
            label = {
                Texts.TitleMedium(
                    text = stringResource(id = R.string.navigation_drawer_delete_notebook),
                    modifier = Modifier.alpha(if (enabledDeleteNotebook) 1f else 0.3f)
                )
            },
            selected = false,
            onClick = { if (enabledDeleteNotebook) onDeleteNotebook() },
        )

        Divider(modifier = Modifier.padding(16.dp))

        if (notebooks.isNotEmpty()) {

            Texts.TitleMedium(
                text = stringResource(id = R.string.navigation_drawer_notebook_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            notebooks.forEach {
                NavigationDrawerItem(
                    label = { Texts.TitleMedium(text = it.title) },
                    selected = it == selectedNotebook,
                    onClick = { onClickNotebook(it) })
            }
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
        enabledDeleteNotebook = false,
        onDeleteNotebook = {},
        onClickNotebook = {}
    )
}
