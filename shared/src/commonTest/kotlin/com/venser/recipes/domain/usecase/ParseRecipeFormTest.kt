package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ParseRecipeFormTest {

    @Test
    fun `parses a valid ingredient row`() {
        val result = parseIngredientInput(name = "Мука", amount = "200", unit = "г")

        assertEquals(Ingredient(name = "Мука", amount = 200.0, unit = "г"), result)
    }

    @Test
    fun `treats a comma as a decimal separator`() {
        val result = parseIngredientInput(name = "Луковица", amount = "0,5", unit = "шт")

        assertEquals(Ingredient(name = "Луковица", amount = 0.5, unit = "шт"), result)
    }

    @Test
    fun `drops an ingredient row with a blank name`() {
        assertNull(parseIngredientInput(name = "  ", amount = "1", unit = "шт"))
    }

    @Test
    fun `drops an ingredient row with a non-numeric amount`() {
        assertNull(parseIngredientInput(name = "Мука", amount = "много", unit = "г"))
    }

    @Test
    fun `parses a step without a timer`() {
        val result = parseStepInput(order = 1, text = "Нарезать овощи", timerMinutesInput = "")

        assertEquals(Step(order = 1, text = "Нарезать овощи", timerSeconds = null), result)
    }

    @Test
    fun `parses a step with a timer given in minutes`() {
        val result = parseStepInput(order = 2, text = "Обжарить лук", timerMinutesInput = "3")

        assertEquals(Step(order = 2, text = "Обжарить лук", timerSeconds = 180), result)
    }

    @Test
    fun `drops a step row with blank text`() {
        assertNull(parseStepInput(order = 1, text = "   ", timerMinutesInput = "5"))
    }
}
