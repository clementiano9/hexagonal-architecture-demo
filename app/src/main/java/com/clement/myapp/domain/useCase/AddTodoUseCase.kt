package com.clement.myapp.domain.useCase

import com.clement.myapp.domain.port.TodoPort
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(private val todoPort: TodoPort) {
    suspend operator fun invoke(body: String) {
        if (body.isNotBlank()) todoPort.addTodo(body)
    }
}