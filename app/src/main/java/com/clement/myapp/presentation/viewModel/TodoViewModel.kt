package com.clement.myapp.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clement.myapp.domain.useCase.AddTodoUseCase
import com.clement.myapp.domain.useCase.DeleteTodoUseCase
import com.clement.myapp.domain.useCase.GetAllTodosUseCase
import com.clement.myapp.domain.useCase.MarkTodoDoneUseCase
import com.clement.myapp.presentation.TodoViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val markTodoDoneUseCase: MarkTodoDoneUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    private val _state: MutableStateFlow<TodoViewState> = MutableStateFlow(
        TodoViewState(isLoading = true, newInput = savedStateHandle["newInput"] ?: "")
    )
    val state = _state.asStateFlow()

    fun updateNewInput(newInput: String) {
        savedStateHandle["newInput"] = newInput
        _state.value = _state.value.copy(newInput = newInput)
    }

    fun loadTodos() = viewModelScope.launch(dispatcher) {
        _state.value = _state.value.copy(isLoading = true)
        getAllTodosUseCase().collect {
            _state.value = _state.value.copy(todos = it, isLoading = false, error = null)
        }
    }

    fun addTodo() = viewModelScope.launch(dispatcher) {
        addTodoUseCase(_state.value.newInput)
        updateNewInput("")
    }

    fun markTodoDone(id: Int, isDone: Boolean) = viewModelScope.launch(dispatcher) {
        markTodoDoneUseCase(id, isDone)
    }

    fun deleteItem(id: Int) = viewModelScope.launch(dispatcher) {
        deleteTodoUseCase(id)
    }
}