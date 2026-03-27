package com.example.futurecodeapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.futurecodeapp.data.User

@Database(entities = [User::class], version = 1)
abstract class CodeDatabase :RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: CodeDatabase? = null

        fun getDatabase(context: Context): CodeDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CodeDatabase::class.java,
                    "user_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}