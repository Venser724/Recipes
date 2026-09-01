package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.domain.model.Recipe

class GetRecipeUseCase(private val repository: RecipeRepository) {
    operator fun invoke(id: Long): Recipe? = repository.getRecipe(id)
}
