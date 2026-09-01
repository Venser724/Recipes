package com.venser.recipes.domain.usecase

import com.venser.recipes.data.ShoppingListRepository

class ToggleShoppingItemUseCase(private val repository: ShoppingListRepository) {
    operator fun invoke(id: Long, checked: Boolean) = repository.setChecked(id, checked)
}
