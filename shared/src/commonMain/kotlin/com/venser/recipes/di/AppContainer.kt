package com.venser.recipes.di

import com.venser.recipes.data.DatabaseDriverFactory
import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.data.ShoppingListRepository
import com.venser.recipes.data.seedRecipesIfEmpty
import com.venser.recipes.db.RecipesDatabase
import com.venser.recipes.domain.usecase.GenerateShoppingListUseCase
import com.venser.recipes.domain.usecase.GetRecipeUseCase
import com.venser.recipes.domain.usecase.GetRecipesUseCase
import com.venser.recipes.domain.usecase.ToggleShoppingItemUseCase

class AppContainer(driverFactory: DatabaseDriverFactory) {
    private val database = RecipesDatabase(driverFactory.createDriver())

    val recipeRepository = RecipeRepository(database)
    val shoppingListRepository = ShoppingListRepository(database)

    val getRecipes = GetRecipesUseCase(recipeRepository)
    val getRecipe = GetRecipeUseCase(recipeRepository)
    val generateShoppingList = GenerateShoppingListUseCase(recipeRepository, shoppingListRepository)
    val toggleShoppingItem = ToggleShoppingItemUseCase(shoppingListRepository)

    init {
        seedRecipesIfEmpty(recipeRepository)
    }
}
