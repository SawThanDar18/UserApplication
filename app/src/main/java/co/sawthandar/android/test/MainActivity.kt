package co.sawthandar.android.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.sawthandar.android.test.ui.UserInputScreen
import co.sawthandar.android.test.ui.theme.UserApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserApplicationTheme {
                UserInputScreen()
            }
        }
    }
}