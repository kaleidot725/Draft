package jp.kaleidot725.emomemo.view.templates.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTemplate(
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    drawerState: DrawerState,
    drawerContent: @Composable (ColumnScope) -> Unit,
    floatingAction: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = drawerContent,
            content = {
                Scaffold(
                    topBar = { topBar() },
                    content = { content() },
                    floatingActionButton = { floatingAction() },
                    modifier = modifier
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MainTemplate_Preview() {
    val drawerState = rememberDrawerState(DrawerValue.Open)

    MainTemplate(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Red)
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        floatingAction = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        Color.Green
                    )
            )
        },
        drawerState = drawerState,
        drawerContent = { Text(text = "DRAWER") },
        modifier = Modifier.fillMaxWidth()
    )
}