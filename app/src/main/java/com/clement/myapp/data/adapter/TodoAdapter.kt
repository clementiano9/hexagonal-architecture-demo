package com.clement.myapp.data.adapter

import com.clement.myapp.data.dao.TodoDao
import com.clement.myapp.domain.model.Todo
import com.clement.myapp.domain.port.TodoPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoAdapter(private val dao: TodoDao) : TodoPort {
    override fun getTodos(): Flow<List<Todo>> {
        return dao.getAll().map { entityList ->
            entityList.map {
                Todo(
                    id = it.id,
                    body = it.body,
                    isDone = it.isDone
                )
            }
        }
    }

    override suspend fun addTodo(text: String) {
        dao.insert(text, false)
    }

    override suspend fun deleteTodo(id: Int) {
        dao.delete(id)
    }

    override suspend fun markAsDone(id: Int, isDone: Boolean) {
        dao.updateIsDone(id, isDone)
    }
}