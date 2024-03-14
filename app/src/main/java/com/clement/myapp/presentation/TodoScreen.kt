package com.clement.myapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.clement.myapp.R
import com.clement.myapp.presentation.component.NewItem
import com.clement.myapp.presentation.component.TodoList
import com.clement.myapp.presentation.viewModel.TodoViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@Composable
fun TodoScreen() {
    val viewModel: TodoViewModel = hiltViewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewState = remember(viewModel.state, lifecycleOwner) {
        viewModel.state.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .stateIn(
                scope = viewModel.viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TodoViewState()
            )
    }.collectAsState()

    LaunchedEffect(key1 = Unit) { viewModel.loadTodos() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.top_bar_label))})
        },
        content = {
            Column(Modifier.padding(it)) {
                Box(Modifier.weight(1f)) {
                    TodoList(
                        list = viewState.value.todos,
                        onCheckedChange = { id, isChecked ->
                            viewModel.markTodoDone(id, isChecked)
                        },
                        onRemoveItem = viewModel::deleteItem
                    )
                }
                NewItem(
                    value = viewState.value.newInput,
                    onValueChange = viewModel::updateNewInput,
                    onAddItem = { viewModel.addTodo() }
                )
            }
        }
    )
}