package com.example.futurecodeapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.futurecodeapp.data.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUsers(users: List<User>)


    @Query("SELECT * FROM users")
     fun getUsers():List<User>
}