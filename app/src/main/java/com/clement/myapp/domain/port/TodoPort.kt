package com.clement.myapp.domain.port

import com.clement.myapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoPort {
    fun getTodos(): Flow<List<Todo>>
    suspend fun addTodo(text: String)
    suspend fun deleteTodo(id: Int)
    suspend fun markAsDone(id: Int, isDone: Boolean)
}