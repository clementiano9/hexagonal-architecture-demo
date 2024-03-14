package com.clement.myapp.domain.useCase

import com.clement.myapp.domain.model.Todo
import com.clement.myapp.domain.port.TodoPort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(private val todoPort: TodoPort) {
    operator fun invoke(): Flow<List<Todo>> = todoPort.getTodos()
}