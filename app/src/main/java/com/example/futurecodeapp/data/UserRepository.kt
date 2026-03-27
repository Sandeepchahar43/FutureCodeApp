package com.example.futurecodeapp.data

import com.example.futurecodeapp.local.UserDao

class UserRepository(private val api: ApiService,
                     private val dao:UserDao) {


    suspend fun getUserData(): List<User> {

        return try {
            val response = api.getUsers()
            val users = response.users

            dao.insertUsers(users)
            users

        } catch (e: Exception) {

            val localData = dao.getUsers()

            if (localData.isEmpty()) {
                emptyList()
            } else {
                localData
            }
        }
    }

}