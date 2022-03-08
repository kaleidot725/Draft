package jp.kaleidot725.emomemo.view.pages.memo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.TextFields
import jp.kaleidot725.emomemo.view.organisms.topbar.MemoTopBar
import jp.kaleidot725.emomemo.view.templates.memo.MemoTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailPage(viewModel: MemoDetailViewModel, onBack: () -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    var title by remember { mutableStateOf("お買い物") }
    var content by remember { mutableStateOf("TEST TEST TEST TEST") }

    MemoTemplate(
        topBar = {
            MemoTopBar(
                title = title,
                modifier = Modifier,
                scrollBehavior = scrollBehavior,
                onClickNavigationIcon = onBack
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                item {
                    TextFields.TitleMedium(text = title, onValueChange = { title = it })
                }

                item {
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))
                }

                item {
                    TextFields.BodyMedium(
                        text = content,
                        onValueChange = { content = it },
                        singleLine = false,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    )
}