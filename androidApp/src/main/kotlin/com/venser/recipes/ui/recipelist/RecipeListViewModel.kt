package com.venser.recipes.ui.recipelist

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.Recipe
import com.venser.recipes.domain.usecase.filterRecipesByTags

class RecipeListViewModel(private val appContainer: AppContainer) : ViewModel() {
    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var allTags by mutableStateOf<List<String>>(emptyList())
        private set

    var selectedTags by mutableStateOf<Set<String>>(emptySet())
        private set

    val filteredRecipes: List<Recipe> by derivedStateOf { filterRecipesByTags(recipes, selectedTags) }

    var selectedRecipeIds by mutableStateOf<Set<Long>>(emptySet())
        private set

    init {
        refresh()
    }

    fun refresh() {
        recipes = appContainer.getRecipes()
        allTags = appContainer.getAllTags()
    }

    fun toggleTag(tag: String) {
        selectedTags = if (tag in selectedTags) selectedTags - tag else selectedTags + tag
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

    fun deleteRecipe(recipeId: Long) {
        appContainer.deleteRecipe(recipeId)
        recipes = recipes.filterNot { it.id == recipeId }
        selectedRecipeIds = selectedRecipeIds - recipeId
    }
}
