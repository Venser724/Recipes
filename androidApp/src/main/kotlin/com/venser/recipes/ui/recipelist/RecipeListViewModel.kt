package com.venser.recipes.ui.recipelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.Recipe

class RecipeListViewModel(private val appContainer: AppContainer) : ViewModel() {
    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var selectedRecipeIds by mutableStateOf<Set<Long>>(emptySet())
        private set

    init {
        refresh()
    }

    fun refresh() {
        recipes = appContainer.getRecipes()
    }

    fun toggleSelected(recipeId: Long) {
        selectedRecipeIds = if (recipeId in selectedRecipeIds) {
            selectedRecipeIds - recipeId
        } else {
            selectedRecipeIds + recipeId
        }
    }

    fun generateShoppingList() {
        appContainer.generateShoppingList(selectedRecipeIds.toList())
        selectedRecipeIds = emptySet()
    }
}
