package com.venser.recipes.domain.usecase

import com.venser.recipes.data.ShoppingListRepository
import com.venser.recipes.domain.model.ShoppingListItem

class GetShoppingListUseCase(private val repository: ShoppingListRepository) {
    operator fun invoke(): List<ShoppingListItem> = repository.getItems()
}
