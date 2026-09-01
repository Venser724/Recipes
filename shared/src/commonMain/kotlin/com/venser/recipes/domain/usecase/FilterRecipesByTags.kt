package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Recipe

fun filterRecipesByTags(recipes: List<Recipe>, selectedTags: Set<String>): List<Recipe> =
    if (selectedTags.isEmpty()) {
        recipes
    } else {
        recipes.filter { recipe -> selectedTags.all { it in recipe.tags } }
    }
