package com.clement.myapp.domain.usecase

import com.clement.myapp.adapter.FakeTodoAdapter
import com.clement.myapp.domain.useCase.DeleteTodoUseCase
import com.clement.myapp.domain.useCase.MarkTodoDoneUseCase
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MarkTodoDoneUseCaseTest {

    private val fakeTodoAdapter = FakeTodoAdapter()

    private val useCase = MarkTodoDoneUseCase(fakeTodoAdapter)

    @Test
    fun `invoke should mark the correct state`() = runBlocking {
        val todo = fakeTodoAdapter.getTodos().first()[0]
        useCase.invoke(id = todo.id, isDone = true)
        val expectedItem = fakeTodoAdapter.getTodos().first().find { it.id == todo.id }

        assertTrue(expectedItem?.isDone ?: false)
    }

}