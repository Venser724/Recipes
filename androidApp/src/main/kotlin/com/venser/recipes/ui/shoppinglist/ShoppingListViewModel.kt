package com.venser.recipes.ui.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.ShoppingListItem

class ShoppingListViewModel(private val appContainer: AppContainer) : ViewModel() {
    var items by mutableStateOf<List<ShoppingListItem>>(emptyList())
        private set

    init {
        refresh()
    }

    fun refresh() {
        items = appContainer.getShoppingList()
    }

    fun toggleChecked(item: ShoppingListItem) {
        appContainer.toggleShoppingItem(item.id, !item.checked)
        refresh()
    }
}
