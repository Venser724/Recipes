package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.AggregatedIngredient
import com.venser.recipes.domain.model.Ingredient
import kotlin.test.Test
import kotlin.test.assertEquals

class AggregateIngredientsTest {

    @Test
    fun `sums amounts for ingredients with same name and unit`() {
        val ingredients = listOf(
            Ingredient(name = "Мука", amount = 200.0, unit = "г"),
            Ingredient(name = "Мука", amount = 300.0, unit = "г"),
        )

        val result = aggregateIngredients(ingredients)

        assertEquals(listOf(AggregatedIngredient(name = "Мука", amount = 500.0, unit = "г")), result)
    }

    @Test
    fun `keeps same ingredient name with different units separate`() {
        val ingredients = listOf(
            Ingredient(name = "Молоко", amount = 200.0, unit = "мл"),
            Ingredient(name = "Молоко", amount = 1.0, unit = "л"),
        )

        val result = aggregateIngredients(ingredients)

        assertEquals(
            listOf(
                AggregatedIngredient(name = "Молоко", amount = 1.0, unit = "л"),
                AggregatedIngredient(name = "Молоко", amount = 200.0, unit = "мл"),
            ),
            result,
        )
    }

    @Test
    fun `leaves unrelated ingredients untouched`() {
        val ingredients = listOf(
            Ingredient(name = "Соль", amount = 5.0, unit = "г"),
            Ingredient(name = "Перец", amount = 2.0, unit = "г"),
        )

        val result = aggregateIngredients(ingredients)

        assertEquals(
            listOf(
                AggregatedIngredient(name = "Перец", amount = 2.0, unit = "г"),
                AggregatedIngredient(name = "Соль", amount = 5.0, unit = "г"),
            ),
            result,
        )
    }

    @Test
    fun `returns empty list for no ingredients`() {
        assertEquals(emptyList(), aggregateIngredients(emptyList()))
    }
}
