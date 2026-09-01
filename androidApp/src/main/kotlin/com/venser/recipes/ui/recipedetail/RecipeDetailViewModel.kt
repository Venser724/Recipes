package com.venser.recipes.ui.recipedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.Recipe

class RecipeDetailViewModel(private val appContainer: AppContainer, private val recipeId: Long) : ViewModel() {
    var recipe by mutableStateOf<Recipe?>(null)
        private set

    init {
        recipe = appContainer.getRecipe(recipeId)
    }

    fun delete() {
        appContainer.deleteRecipe(recipeId)
    }
}
