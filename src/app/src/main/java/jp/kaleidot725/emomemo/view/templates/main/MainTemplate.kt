package jp.kaleidot725.emomemo.view.templates.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTemplate(topBar: @Composable () -> Unit, mainContent: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { topBar() },
        content = { mainContent() },
        modifier = modifier
    )
}

@Preview
@Composable
private fun MainTemplate_Preview() {
    MainTemplate(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Red)
            )
        }, mainContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}