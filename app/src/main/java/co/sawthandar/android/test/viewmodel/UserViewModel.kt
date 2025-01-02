package co.sawthandar.android.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sawthandar.android.test.persistence.dao.UserDao
import co.sawthandar.android.test.persistence.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _allUsers = MutableStateFlow<List<UserEntity>>(emptyList())
    val allUsers: StateFlow<List<UserEntity>> = _allUsers

    fun fetchAllUsers() {
        viewModelScope.launch {
            _allUsers.value = userDao.getAllUsers()
        }
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.updateUser(user)
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            userDao.deleteUserById(userId)
        }
    }
}
