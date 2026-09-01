package com.venser.recipes.ui.shoppinglist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.ShoppingListItem
import com.venser.recipes.ui.formatAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(appContainer: AppContainer) {
    val viewModel: ShoppingListViewModel = viewModel(
        factory = viewModelFactory { initializer { ShoppingListViewModel(appContainer) } },
    )

    Scaffold(topBar = { TopAppBar(title = { Text("Список покупок") }) }) { paddingValues ->
        if (viewModel.items.isEmpty()) {
            Text(
                "Список пуст. Выберите рецепты на главном экране.",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
            )
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            items(viewModel.items, key = { it.id }) { item ->
                ShoppingListRow(item = item, onToggle = { viewModel.toggleChecked(item) })
            }
        }
    }
}

@Composable
private fun ShoppingListRow(item: ShoppingListItem, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = item.checked, onCheckedChange = { onToggle() })
        Text(
            text = "${item.name} — ${formatAmount(item.amount)} ${item.unit}",
            textDecoration = if (item.checked) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}
