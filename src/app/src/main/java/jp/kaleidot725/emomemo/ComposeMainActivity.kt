package jp.kaleidot725.emomemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import jp.kaleidot725.emomemo.view.pages.main.MainPage
import jp.kaleidot725.emomemo.view.pages.main.MainViewModel

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainPage(MainViewModel())
            }
        }
    }
}