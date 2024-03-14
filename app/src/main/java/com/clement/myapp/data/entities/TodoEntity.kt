package com.clement.myapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val isDone: Boolean
)