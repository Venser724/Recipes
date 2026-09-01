package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.domain.model.Recipe

class GetRecipesUseCase(private val repository: RecipeRepository) {
    operator fun invoke(): List<Recipe> = repository.getRecipes()
}
