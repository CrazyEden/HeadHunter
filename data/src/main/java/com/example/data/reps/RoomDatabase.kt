package com.example.data.reps

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.model.RoomDataEntity

@Database(
    entities = [RoomDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacanciesDao(): VacanciesDao
    companion object{
        private var dbInstance: AppDatabase? = null
        fun getInstance(context:Context): AppDatabase {
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "xdd"
                ).fallbackToDestructiveMigration().build()
            }
            return dbInstance!!
        }
    }
}