package co.sawthandar.android.test.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.sawthandar.android.test.persistence.entity.UserEntity
import co.sawthandar.android.test.viewmodel.UserViewModel

@Composable
fun UserInputScreen(viewModel: UserViewModel, navController: NavController) {
    val users = viewModel.allUsers.collectAsState()

    val firstname = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    LaunchedEffect (Unit) {
        viewModel.fetchAllUsers()
    }

    val hasUserData = users.value.isNotEmpty()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(
            value = firstname.value,
            onValueChange = { firstname.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lastname.value,
            onValueChange = { lastname.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val user = UserEntity(firstname = firstname.value, lastname = lastname.value, email = email.value)
                viewModel.addUser(user)
                viewModel.fetchAllUsers()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save User")
        }

        if (hasUserData) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("userListScreen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to User List")
            }
        }
    }
}
