package com.venser.recipes.domain.model

data class Recipe(
    val id: Long,
    val title: String,
    val tags: List<String>,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
)
