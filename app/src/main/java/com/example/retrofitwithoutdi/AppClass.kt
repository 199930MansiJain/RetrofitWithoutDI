package com.example.retrofitwithoutdi

import android.app.Application
import androidx.room.Room
import com.example.retrofitwithoutdi.sqlDataBase.AppDatabase

class AppClass : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        ).build()
    }
}