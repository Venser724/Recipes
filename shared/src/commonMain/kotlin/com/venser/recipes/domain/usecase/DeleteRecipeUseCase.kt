package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository

class DeleteRecipeUseCase(private val repository: RecipeRepository) {
    operator fun invoke(id: Long) = repository.deleteRecipe(id)
}
