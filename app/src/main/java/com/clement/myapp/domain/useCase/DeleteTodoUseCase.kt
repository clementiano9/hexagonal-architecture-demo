package com.clement.myapp.domain.useCase

import com.clement.myapp.domain.port.TodoPort
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(private val todoPort: TodoPort) {
    suspend operator fun invoke(id: Int) = todoPort.deleteTodo(id)
}