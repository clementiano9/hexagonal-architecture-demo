package com.clement.myapp.domain.useCase

import com.clement.myapp.domain.port.TodoPort
import javax.inject.Inject

class MarkTodoDoneUseCase @Inject constructor(private val todoPort: TodoPort) {
    suspend operator fun invoke(id: Int, isDone: Boolean) = todoPort.markAsDone(id, isDone)
}