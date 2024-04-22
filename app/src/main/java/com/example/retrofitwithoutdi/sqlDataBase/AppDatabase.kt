package com.example.retrofitwithoutdi.sqlDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retrofitwithoutdi.sqlDataBase.dao.User
import com.example.retrofitwithoutdi.sqlDataBase.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}