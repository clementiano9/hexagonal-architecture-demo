package com.clement.myapp.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clement.myapp.presentation.theme.MyAppTheme

/**
 * TodoItem is a composable that represents a single item in the todo list.
 */
@Composable
fun TodoItem(
    body: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onRemoveItem: () -> Unit = {}
) {
    // A checkbox and a text field
    Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Text(
            text = body,
            Modifier
                .weight(1f)
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .align(Alignment.CenterVertically)
                .alpha(if (isChecked) 0.5f else 1f),
        )
        IconButton(onClick = onRemoveItem) {
            Icon(Icons.Filled.Delete, contentDescription = null, tint = Color.Red)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TodoItemPreview() {
    MyAppTheme {
        TodoItem(body = "Item 1", isChecked = false, onCheckedChange = {})
    }
}

@Composable
@Preview(showBackground = true)
fun TodoItemCheckedPreview() {
    MyAppTheme {
        TodoItem(body = "Item 1", isChecked = true, onCheckedChange = {})
    }
}