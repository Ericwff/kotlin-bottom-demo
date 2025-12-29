package com.example.bottomnavigationapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnavigationapplication.database.dao.CourseDao
import com.example.bottomnavigationapplication.model.Course

@Database(entities = [Course::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

}