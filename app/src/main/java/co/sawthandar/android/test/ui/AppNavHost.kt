package co.sawthandar.android.test.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.sawthandar.android.test.viewmodel.UserViewModel

@Composable
fun AppNavHost(navController: NavHostController, viewModel: UserViewModel) {
    NavHost(navController = navController, startDestination = "userInputScreen") {
        composable("userInputScreen") {
            UserInputScreen(viewModel = viewModel, navController = navController)
        }
        composable("userListScreen") {
            UserListScreen(viewModel = viewModel)
        }
    }
}
