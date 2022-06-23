package jp.kaleidot725.emomemo.view.organisms.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.MoreVertical
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun MemoTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onClickNavigationIcon: (() -> Unit)? = null,
    onDeleteMemo: (() -> Unit)? = null
) {
    SmallTopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Texts.TitleLarge(
                    text = title, maxLines = 1, modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Center)
                )
            }
        },
        actions = {
            IconButton(onClick = { onDeleteMemo?.invoke() }) {
                Icon(FeatherIcons.MoreVertical, contentDescription = "MoreVertical")
            }
        },
        navigationIcon = {
            Icon(
                imageVector = FeatherIcons.ArrowLeft,
                contentDescription = "ArrowLeft",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { onClickNavigationIcon?.invoke() }
            )
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MemoTopBar_Preview() {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(TopAppBarScrollState(0f, 0f, 0f)) }
    MemoTopBar(
        title = "お買い物",
        modifier = Modifier,
    )
}
