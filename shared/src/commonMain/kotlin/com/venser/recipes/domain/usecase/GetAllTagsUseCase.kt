package com.venser.recipes.domain.usecase

import com.venser.recipes.data.RecipeRepository

class GetAllTagsUseCase(private val repository: RecipeRepository) {
    operator fun invoke(): List<String> = repository.getAllTags()
}
