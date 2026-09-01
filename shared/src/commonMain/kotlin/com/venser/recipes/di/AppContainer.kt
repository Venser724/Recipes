package com.venser.recipes.di

import com.venser.recipes.data.DatabaseDriverFactory
import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.data.ShoppingListRepository
import com.venser.recipes.data.seedRecipesIfEmpty
import com.venser.recipes.db.RecipesDatabase
import com.venser.recipes.domain.usecase.GenerateShoppingListUseCase
import com.venser.recipes.domain.usecase.GetAllTagsUseCase
import com.venser.recipes.domain.usecase.GetRecipeUseCase
import com.venser.recipes.domain.usecase.GetRecipesUseCase
import com.venser.recipes.domain.usecase.GetShoppingListUseCase
import com.venser.recipes.domain.usecase.ToggleShoppingItemUseCase

class AppContainer(driverFactory: DatabaseDriverFactory) {
    private val database = RecipesDatabase(driverFactory.createDriver())

    private val recipeRepository = RecipeRepository(database)
    private val shoppingListRepository = ShoppingListRepository(database)

    val getRecipes = GetRecipesUseCase(recipeRepository)
    val getRecipe = GetRecipeUseCase(recipeRepository)
    val getAllTags = GetAllTagsUseCase(recipeRepository)
    val getShoppingList = GetShoppingListUseCase(shoppingListRepository)
    val generateShoppingList = GenerateShoppingListUseCase(recipeRepository, shoppingListRepository)
    val toggleShoppingItem = ToggleShoppingItemUseCase(shoppingListRepository)

    init {
        seedRecipesIfEmpty(recipeRepository)
    }
}
