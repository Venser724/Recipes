package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step

fun parseIngredientInput(name: String, amount: String, unit: String): Ingredient? {
    val trimmedName = name.trim()
    val parsedAmount = amount.trim().replace(',', '.').toDoubleOrNull()
    if (trimmedName.isEmpty() || parsedAmount == null) return null
    return Ingredient(name = trimmedName, amount = parsedAmount, unit = unit.trim())
}

fun parseStepInput(order: Int, text: String, timerMinutesInput: String): Step? {
    val trimmedText = text.trim()
    if (trimmedText.isEmpty()) return null
    val timerSeconds = timerMinutesInput.trim().toIntOrNull()?.times(60)
    return Step(order = order, text = trimmedText, timerSeconds = timerSeconds)
}
