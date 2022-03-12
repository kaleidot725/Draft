package jp.kaleidot725.emomemo.view.pages.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.systemBarsPadding
import jp.kaleidot725.emomemo.view.atoms.TextFields
import jp.kaleidot725.emomemo.view.organisms.topbar.MemoTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailPage(viewModel: MemoDetailViewModel, onBack: () -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()
    var title by remember { mutableStateOf("お買い物") }
    var content by remember { mutableStateOf(uiState.test) }

    Scaffold(
        topBar = {
            MemoTopBar(
                title = title,
                onClickNavigationIcon = onBack
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsWithImePadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextFields.BodyLarge(
                    text = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()

                TextFields.BodyMedium(
                    text = content,
                    onValueChange = { content = it },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier.systemBarsPadding()
    )
}