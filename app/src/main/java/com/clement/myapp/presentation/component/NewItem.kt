package com.clement.myapp.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clement.myapp.R

/**
 * Input field for adding new items to the list
 */
@Composable
fun NewItem(value: String = "", onValueChange: (String) -> Unit, onAddItem: () -> Unit) {
    Row(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier.fillMaxWidth(0.85f),
            placeholder = { Text(text = stringResource(R.string.input_placeholder)) }
        )
        IconButton(onClick = { onAddItem() },
        Modifier.padding(horizontal = 8.dp)) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }
}

@Preview()
@Composable
fun NewItemPreview() {
    NewItem(value = "", onValueChange = {}, onAddItem = {})
}