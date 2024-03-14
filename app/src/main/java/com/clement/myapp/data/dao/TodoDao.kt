package com.clement.myapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.clement.myapp.data.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the todoDb table
 */
@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAll(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: Int): TodoEntity

    @Query("INSERT INTO todo (body, isDone) VALUES (:body, :isDone)")
    suspend fun insert(body: String, isDone: Boolean? = false)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("UPDATE todo SET isDone = :isDone WHERE id = :id")
    suspend fun updateIsDone(id: Int, isDone: Boolean)

}