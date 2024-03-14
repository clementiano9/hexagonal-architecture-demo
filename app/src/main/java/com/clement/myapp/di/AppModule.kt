package com.clement.myapp.di

import com.clement.myapp.data.adapter.TodoAdapter
import com.clement.myapp.data.dao.TodoDao
import com.clement.myapp.domain.port.TodoPort
import com.clement.myapp.domain.useCase.AddTodoUseCase
import com.clement.myapp.domain.useCase.DeleteTodoUseCase
import com.clement.myapp.domain.useCase.GetAllTodosUseCase
import com.clement.myapp.domain.useCase.MarkTodoDoneUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesTodoPort(todoDao: TodoDao): TodoPort = TodoAdapter(todoDao)

    @Provides
    @Singleton
    fun providesGetAllTodosUseCase(todoPort: TodoPort) = GetAllTodosUseCase(todoPort)

    @Provides
    @Singleton
    fun providesAddTodoUseCase(todoPort: TodoPort) = AddTodoUseCase(todoPort)

    @Provides
    @Singleton
    fun providesMarkTodoDoneUseCase(todoPort: TodoPort) = MarkTodoDoneUseCase(todoPort)

    @Provides
    @Singleton
    fun providesDeleteTodoUseCase(todoPort: TodoPort) = DeleteTodoUseCase(todoPort)

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}