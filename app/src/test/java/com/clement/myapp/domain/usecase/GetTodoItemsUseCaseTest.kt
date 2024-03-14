package com.clement.myapp.domain.usecase

import com.clement.myapp.adapter.FakeTodoAdapter
import com.clement.myapp.domain.useCase.GetAllTodosUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetTodoItemsUseCaseTest {

    private val fakeTodoAdapter = FakeTodoAdapter()

    private val useCase = GetAllTodosUseCase(fakeTodoAdapter)

    @Test
    fun `invoke should return correct flow`() = runBlocking {
        val resultFlow = useCase.invoke()
        val expectedFlow = fakeTodoAdapter.getTodos()

        assertEquals(expectedFlow.first(), resultFlow.first())
    }

}