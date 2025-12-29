package dev.marcosfarias.pokedex.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bottomnavigationapplication.database.AppDatabase
import okhttp3.internal.platform.PlatformRegistry.applicationContext


val MIGRATION_5_6: Migration = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 在这里执行SQL语句来修改表结构
        // 例如，添加一个新字段
//        database.execSQL("ALTER TABLE YourTable ADD COLUMN newColumn TEXT")
    }
}

val db = Room.databaseBuilder(
    applicationContext!!,
    AppDatabase::class.java,
    "study.db"
)
    .allowMainThreadQueries()
//    .addMigrations(MIGRATION_5_6)
    .build()

val courseDao = db.courseDao()