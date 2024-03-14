package com.clement.myapp.di

import android.content.Context
import androidx.room.Room
import com.clement.myapp.data.dao.TodoDao
import com.clement.myapp.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(@ApplicationContext context: Context): TodoDatabase =
        Room.databaseBuilder(context, TodoDatabase::class.java, "todo-database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesTodoDao(todoDatabase: TodoDatabase): TodoDao = todoDatabase.todoDao()
}