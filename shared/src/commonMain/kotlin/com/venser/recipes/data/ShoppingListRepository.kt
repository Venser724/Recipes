package com.venser.recipes.data

import com.venser.recipes.db.RecipesDatabase
import com.venser.recipes.db.ShoppingListItem as ShoppingListItemRow
import com.venser.recipes.domain.model.AggregatedIngredient
import com.venser.recipes.domain.model.ShoppingListItem

class ShoppingListRepository(private val database: RecipesDatabase) {

    fun getItems(): List<ShoppingListItem> =
        database.recipesQueries.selectAllShoppingItems().executeAsList().map(::toShoppingListItem)

    fun replaceWith(items: List<AggregatedIngredient>) {
        database.transaction {
            database.recipesQueries.clearShoppingList()
            items.forEach { item ->
                database.recipesQueries.insertShoppingItem(
                    name = item.name,
                    amount = item.amount,
                    unit = item.unit,
                )
            }
        }
    }

    fun setChecked(id: Long, checked: Boolean) {
        database.recipesQueries.setShoppingItemChecked(checked = if (checked) 1L else 0L, id = id)
    }

    private fun toShoppingListItem(row: ShoppingListItemRow) = ShoppingListItem(
        id = row.id,
        name = row.name,
        amount = row.amount,
        unit = row.unit,
        checked = row.checked == 1L,
    )
}
