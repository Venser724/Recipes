package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Recipe
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterRecipesByTagsTest {

    private fun recipe(id: Long, title: String, tags: List<String>) = Recipe(
        id = id,
        title = title,
        tags = tags,
        servings = 1,
        ingredients = emptyList(),
        steps = emptyList(),
    )

    @Test
    fun `returns all recipes when no tags selected`() {
        val recipes = listOf(recipe(1, "A", listOf("Завтрак")), recipe(2, "B", listOf("Паста")))

        assertEquals(recipes, filterRecipesByTags(recipes, emptySet()))
    }

    @Test
    fun `keeps only recipes that have all selected tags`() {
        val pastaWithMeat = recipe(1, "Паста с мясом", listOf("Паста", "Мясо"))
        val salad = recipe(2, "Салат", listOf("Салат"))

        val result = filterRecipesByTags(listOf(pastaWithMeat, salad), setOf("Паста", "Мясо"))

        assertEquals(listOf(pastaWithMeat), result)
    }

    @Test
    fun `drops recipes missing at least one selected tag`() {
        val pasta = recipe(1, "Паста", listOf("Паста"))

        val result = filterRecipesByTags(listOf(pasta), setOf("Паста", "Мясо"))

        assertEquals(emptyList(), result)
    }
}
