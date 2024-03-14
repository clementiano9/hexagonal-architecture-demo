package com.clement.myapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clement.myapp.data.dao.TodoDao
import com.clement.myapp.data.entities.TodoEntity


@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase(): RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "TodoDatabase"
    }
}