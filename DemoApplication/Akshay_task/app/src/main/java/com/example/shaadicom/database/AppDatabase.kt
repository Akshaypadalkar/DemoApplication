package com.example.shaadicom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shaadicom.BaseApplication

@Database(entities = [UserDetailsEntity::class],
    version = 1)
abstract  class AppDatabase:RoomDatabase() {
    abstract fun userdeatislDao(): UserDetailsDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(BaseApplication.context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            val DB_NAME = "Shaadi.db"
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DB_NAME
            ).allowMainThreadQueries()
                .build()
        }
    }
}
