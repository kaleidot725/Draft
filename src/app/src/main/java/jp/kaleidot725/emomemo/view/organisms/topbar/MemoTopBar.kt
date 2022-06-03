package jp.kaleidot725.emomemo.view.organisms.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.MoreVertical
import jp.kaleidot725.emomemo.view.atoms.BasicTextFields

@Composable
fun MemoTopBar(
    title: String,
    onChangeTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onClickNavigationIcon: (() -> Unit)? = null,
    onDeleteMemo: (() -> Unit)? = null
) {
    SmallTopAppBar(
        title = {
            BasicTextFields.TitleLarge(
                text = title,
                onValueChange = onChangeTitle,
                modifier = modifier.padding(16.dp)
            )
        },
        actions = {
            IconButton(onClick = { onDeleteMemo?.invoke() }) {
                Icon(FeatherIcons.MoreVertical, contentDescription = "Delete text")
            }
        },
        navigationIcon = {
            Icon(
                imageVector = FeatherIcons.ArrowLeft,
                contentDescription = "ArrowBack",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { onClickNavigationIcon?.invoke() }
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MemoTopBar_Preview() {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    MemoTopBar(
        title = "お買い物",
        onChangeTitle = {},
        modifier = Modifier,
        scrollBehavior = scrollBehavior
    )
}
