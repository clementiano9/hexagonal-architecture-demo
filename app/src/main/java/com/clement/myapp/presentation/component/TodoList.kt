package com.clement.myapp.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.clement.myapp.domain.model.Todo
import com.clement.myapp.presentation.theme.MyAppTheme

@Composable
fun TodoList(
    list: List<Todo>,
    onCheckedChange: (Int, Boolean) -> Unit = { _, _ -> },
    onRemoveItem: (Int) -> Unit = {}
) {
    LazyColumn() {
        list.map { todo ->
            item {
                TodoItem(
                    body = todo.body,
                    isChecked = todo.isDone,
                    onCheckedChange = {
                        onCheckedChange(todo.id, it)
                    }, onRemoveItem = {
                        onRemoveItem(todo.id)
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    val list = listOf(
        Todo(
            id = 1,
            body = "Item 1",
            isDone = false
        ),
        Todo(
            id = 2,
            body = "Item 2",
            isDone = true
        )
    )
    MyAppTheme {
        TodoList(list = list)
    }
}