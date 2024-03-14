package com.clement.myapp.viewModel

import androidx.lifecycle.SavedStateHandle
import com.clement.myapp.adapter.FakeTodoAdapter
import com.clement.myapp.domain.model.Todo
import com.clement.myapp.domain.useCase.AddTodoUseCase
import com.clement.myapp.domain.useCase.DeleteTodoUseCase
import com.clement.myapp.domain.useCase.GetAllTodosUseCase
import com.clement.myapp.domain.useCase.MarkTodoDoneUseCase
import com.clement.myapp.presentation.viewModel.TodoViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {

    private val fakeTodoAdapter = FakeTodoAdapter()
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
    private lateinit var viewModel: TodoViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        viewModel = TodoViewModel(
            savedStateHandle = savedStateHandle,
            getAllTodosUseCase = GetAllTodosUseCase(todoPort = fakeTodoAdapter),
            markTodoDoneUseCase = MarkTodoDoneUseCase(todoPort = fakeTodoAdapter),
            addTodoUseCase = AddTodoUseCase(todoPort = fakeTodoAdapter),
            deleteTodoUseCase = DeleteTodoUseCase(todoPort = fakeTodoAdapter),
            dispatcher = testDispatcher
        )

        // Load items first
        viewModel.loadTodos()
        testDispatcher.scheduler.advanceUntilIdle()
    }

    @Test
    fun `updateNewInput should update newInput`() {
        val newInput = "new input"
        viewModel.updateNewInput(newInput)
        assert(viewModel.state.value.newInput == newInput)
    }

    @Test
    fun `loadTodos should update todos`() = runTest() {
        val expected = fakeTodoAdapter.getTodos().first()
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected.size, viewModel.state.value.todos.size)
    }

    @Test
    fun `addTodo should add new todo`() = runTest() {
        val newTodo = "new todo"
        viewModel.updateNewInput(newTodo)
        viewModel.addTodo()

        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.state.value.todos.last()

        assertEquals(newTodo, result.body)
    }

    @Test
    fun `markTodoDone should update todo`() = runTest {
        val todo = viewModel.state.value.todos[0]
        viewModel.markTodoDone(todo.id, true)
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.state.value.todos.first { it.id == todo.id }

        assert(result.isDone)
    }

    @Test
    fun `deleteItem should remove todo`() = runTest {
        val newTodo = "new todo"
        viewModel.updateNewInput(newTodo)
        viewModel.addTodo()
        val todo = viewModel.state.value.todos[0]
        viewModel.deleteItem(todo.id)

        testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.state.value.todos.none { it.id == todo.id })
    }

}