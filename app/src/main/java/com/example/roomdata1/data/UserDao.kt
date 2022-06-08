package com.example.roomdata1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdata1.model.User
import java.util.concurrent.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("Select * from user_table")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Query("select * from user_table where firstName like:searchQuery or lastName like:searchQuery")
    fun searchUser(searchQuery: String): kotlinx.coroutines.flow.Flow<List<User>>
}