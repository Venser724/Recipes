package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ParseRecipeJsonTest {

    @Test
    fun `parses a full recipe`() {
        val json = """
            {
              "title": "Блинчики",
              "tags": ["Завтрак", "Сладкое"],
              "servings": 4,
              "ingredients": [
                { "name": "Мука", "amount": 200, "unit": "г" }
              ],
              "steps": [
                { "text": "Смешать ингредиенты", "timerSeconds": 120 },
                { "text": "Жарить блины" }
              ],
              "notes": "Заметка"
            }
        """.trimIndent()

        val result = parseRecipeJson(json).getOrThrow()

        assertEquals("Блинчики", result.title)
        assertEquals(listOf("Завтрак", "Сладкое"), result.tags)
        assertEquals(4, result.servings)
        assertEquals(listOf(Ingredient(name = "Мука", amount = 200.0, unit = "г")), result.ingredients)
        assertEquals(
            listOf(
                Step(order = 1, text = "Смешать ингредиенты", timerSeconds = 120),
                Step(order = 2, text = "Жарить блины", timerSeconds = null),
            ),
            result.steps,
        )
        assertEquals("Заметка", result.notes)
    }

    @Test
    fun `defaults tags, notes and ingredients steps when omitted`() {
        val json = """{ "title": "Простой рецепт", "servings": 2 }"""

        val result = parseRecipeJson(json).getOrThrow()

        assertEquals(emptyList(), result.tags)
        assertTrue(result.ingredients.isEmpty())
        assertTrue(result.steps.isEmpty())
        assertEquals(null, result.notes)
    }

    @Test
    fun `fails when title is missing`() {
        val json = """{ "servings": 2 }"""

        assertTrue(parseRecipeJson(json).isFailure)
    }

    @Test
    fun `fails when title is blank`() {
        val json = """{ "title": "   ", "servings": 2 }"""

        assertTrue(parseRecipeJson(json).isFailure)
    }

    @Test
    fun `fails when servings is missing`() {
        val json = """{ "title": "Рецепт" }"""

        assertTrue(parseRecipeJson(json).isFailure)
    }

    @Test
    fun `fails when servings is not positive`() {
        val json = """{ "title": "Рецепт", "servings": 0 }"""

        assertTrue(parseRecipeJson(json).isFailure)
    }

    @Test
    fun `fails on malformed json`() {
        assertTrue(parseRecipeJson("not json at all").isFailure)
    }

    @Test
    fun `strips markdown code fence with json language tag`() {
        val json = """
            ```json
            { "title": "Рецепт", "servings": 2 }
            ```
        """.trimIndent()

        assertTrue(parseRecipeJson(json).isSuccess)
    }

    @Test
    fun `strips bare markdown code fence`() {
        val json = """
            ```
            { "title": "Рецепт", "servings": 2 }
            ```
        """.trimIndent()

        assertTrue(parseRecipeJson(json).isSuccess)
    }

    @Test
    fun `ignores leading prose before the json object`() {
        val json = """
            Вот JSON:
            { "title": "Рецепт", "servings": 2 }
        """.trimIndent()

        assertTrue(parseRecipeJson(json).isSuccess)
    }

    @Test
    fun `ignores trailing prose after the json object`() {
        val json = """
            { "title": "Рецепт", "servings": 2 }
            Надеюсь, это поможет!
        """.trimIndent()

        assertTrue(parseRecipeJson(json).isSuccess)
    }

    @Test
    fun `allows a trailing comma in an object`() {
        val json = """{ "title": "Рецепт", "servings": 2, }"""

        assertTrue(parseRecipeJson(json).isSuccess)
    }

    @Test
    fun `allows a trailing comma in an array`() {
        val json = """
            {
              "title": "Рецепт",
              "servings": 2,
              "tags": ["Завтрак",]
            }
        """.trimIndent()

        assertTrue(parseRecipeJson(json).isSuccess)
    }
}
