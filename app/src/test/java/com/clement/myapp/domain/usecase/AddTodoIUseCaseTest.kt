package com.clement.myapp.domain.usecase

import com.clement.myapp.adapter.FakeTodoAdapter
import com.clement.myapp.domain.useCase.AddTodoUseCase
import com.clement.myapp.domain.useCase.GetAllTodosUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AddTodoIUseCaseTest {

    private val fakeTodoAdapter = FakeTodoAdapter()

    private val useCase = AddTodoUseCase(fakeTodoAdapter)

    @Test
    fun `invoke should add item`() = runBlocking {
        useCase.invoke(body = "New item")
        val expectedFlow = fakeTodoAdapter.getTodos()

        assertNotNull(expectedFlow.first().filter { it.body == "New item" })
    }

}