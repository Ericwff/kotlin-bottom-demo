package com.example.bottomnavigationapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//@TypeConverters(ListStringConverter::class)
data class Course(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "label") val label:String,
    @ColumnInfo(name = "poster") val poster:String,
    @ColumnInfo(name = "progress") var progress:String,
)