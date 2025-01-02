package co.sawthandar.android.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import co.sawthandar.android.test.persistence.database.UserDatabase
import co.sawthandar.android.test.ui.UserInputScreen
import co.sawthandar.android.test.ui.theme.UserApplicationTheme
import co.sawthandar.android.test.viewmodel.UserViewModel
import co.sawthandar.android.test.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDao = UserDatabase.getDatabase(applicationContext).userDao()

        enableEdgeToEdge()
        setContent {
            val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(userDao))

            UserApplicationTheme {
                UserInputScreen(userViewModel)
            }
        }
    }
}