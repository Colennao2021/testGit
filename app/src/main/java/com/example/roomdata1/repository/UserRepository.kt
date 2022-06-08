package com.example.roomdata1.repository

import androidx.lifecycle.LiveData
import com.example.roomdata1.model.User
import com.example.roomdata1.data.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    //tai sao khong phai suspent
    fun searchUser(searchQuery: String): Flow<List<User>> {
        return userDao.searchUser(searchQuery)
    }

}