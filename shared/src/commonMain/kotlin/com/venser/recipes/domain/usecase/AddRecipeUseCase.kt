package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository
import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step

class AddRecipeUseCase(private val repository: RecipeRepository) {
    operator fun invoke(
        title: String,
        tags: List<String>,
        servings: Int,
        ingredients: List<Ingredient>,
        steps: List<Step>,
        notes: String?,
    ) = repository.addRecipe(
        title = title,
        tags = tags,
        servings = servings,
        ingredients = ingredients,
        steps = steps,
        notes = notes,
    )
}
