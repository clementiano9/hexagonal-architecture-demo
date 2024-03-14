package com.clement.myapp.adapter

import com.clement.myapp.domain.model.Todo
import com.clement.myapp.domain.port.TodoPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeTodoAdapter(): TodoPort {

    private val mockTodoList = listOf(
        Todo(
            id = 1,
            body = "Item 1",
            isDone = false
        ),
        Todo(
            id = 2,
            body = "Item 2",
            isDone = false
        )
    )

    private val fakeDb: MutableList<Todo> = mockTodoList.toMutableList()

    override fun getTodos(): Flow<List<Todo>> {
        return flowOf(fakeDb)
    }

    override suspend fun addTodo(text: String) {
        fakeDb.add(Todo(
            id = fakeDb.size + 1,
            body = text,
            isDone = false
        ))
    }

    override suspend fun deleteTodo(id: Int) {
        fakeDb.removeIf { it.id == id }
    }

    override suspend fun markAsDone(id: Int, isDone: Boolean) {
        val updatedItem = fakeDb.find { it.id == id }?.copy(isDone = isDone)
        fakeDb.removeIf { it.id == id }
        fakeDb.add(updatedItem!!)
    }
}