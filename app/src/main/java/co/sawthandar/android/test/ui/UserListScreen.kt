package co.sawthandar.android.test.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.sawthandar.android.test.viewmodel.UserViewModel

@Composable
fun UserListScreen(viewModel: UserViewModel) {
    val users = viewModel.allUsers.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllUsers()
    }

    AnimatedVisibility(
        users.value.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)) {
            items(users.value.size) { index ->
                val user = users.value[index]

                val firstname = remember { mutableStateOf(user.firstname) }
                val lastname = remember { mutableStateOf(user.lastname) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = firstname.value,
                        onValueChange = { firstname.value = it },
                        label = { Text("First Name") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = lastname.value,
                        onValueChange = { lastname.value = it },
                        label = { Text("Last Name") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        val updatedUser = user.copy(firstname = firstname.value, lastname = lastname.value)

                        viewModel.updateUser(updatedUser)
                        viewModel.fetchAllUsers()
                    }) {
                        Text("Update")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        viewModel.deleteUser(user.id)
                        viewModel.fetchAllUsers()
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
