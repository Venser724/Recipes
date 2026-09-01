package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.data.ShoppingListRepository

class GenerateShoppingListUseCase(
    private val recipeRepository: RecipeRepository,
    private val shoppingListRepository: ShoppingListRepository,
) {
    operator fun invoke(recipeIds: List<Long>) {
        val ingredients = recipeIds.flatMap { id -> recipeRepository.getRecipe(id)?.ingredients.orEmpty() }
        shoppingListRepository.replaceWith(aggregateIngredients(ingredients))
    }
}
