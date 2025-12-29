package com.example.bottomnavigationapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bottomnavigationapplication.model.Course

@Dao
interface CourseDao {

    @Insert
    suspend fun insert(course: Course)

    @Query("SELECT * FROM course")
    suspend  fun getAllCourses(): MutableList<Course>

}