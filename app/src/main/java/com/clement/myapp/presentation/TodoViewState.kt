package com.clement.myapp.presentation

import com.clement.myapp.domain.model.Todo

data class TodoViewState(
    val newInput: String = "",
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
    val error: String? = null
)
