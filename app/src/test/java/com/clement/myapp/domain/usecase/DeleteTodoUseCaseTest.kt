package com.clement.myapp.domain.usecase

import com.clement.myapp.adapter.FakeTodoAdapter
import com.clement.myapp.domain.useCase.DeleteTodoUseCase
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DeleteTodoUseCaseTest {

    private val fakeTodoAdapter = FakeTodoAdapter()

    private val useCase = DeleteTodoUseCase(fakeTodoAdapter)

    @Test
    fun `invoke should delete item`() = runBlocking {
        val todo = fakeTodoAdapter.getTodos().first()[0]
        useCase.invoke(id = todo.id)
        val expectedFlow = fakeTodoAdapter.getTodos()

        assertNull(expectedFlow.first().find { it.body == "New item" })
    }

}