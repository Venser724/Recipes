package com.venser.recipes.ui.addrecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.usecase.parseIngredientInput
import com.venser.recipes.domain.usecase.parseStepInput

class IngredientFormRow {
    var name by mutableStateOf("")
    var amount by mutableStateOf("")
    var unit by mutableStateOf("")
}

class StepFormRow {
    var text by mutableStateOf("")
    var timerMinutes by mutableStateOf("")
}

class AddRecipeViewModel(private val appContainer: AppContainer) : ViewModel() {
    var title by mutableStateOf("")
    var tagsInput by mutableStateOf("")
    var notes by mutableStateOf("")

    var servings by mutableStateOf(2)
        private set

    val ingredientRows = mutableStateListOf(IngredientFormRow())
    val stepRows = mutableStateListOf(StepFormRow())

    fun incrementServings() {
        servings += 1
    }

    fun decrementServings() {
        if (servings > 1) servings -= 1
    }

    fun addIngredientRow() {
        ingredientRows.add(IngredientFormRow())
    }

    fun removeIngredientRow(row: IngredientFormRow) {
        if (ingredientRows.size > 1) ingredientRows.remove(row)
    }

    fun addStepRow() {
        stepRows.add(StepFormRow())
    }

    fun removeStepRow(row: StepFormRow) {
        if (stepRows.size > 1) stepRows.remove(row)
    }

    fun save() {
        val tags = tagsInput.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val ingredients = ingredientRows.mapNotNull { row ->
            parseIngredientInput(name = row.name, amount = row.amount, unit = row.unit)
        }
        val steps = stepRows
            .mapNotNull { row -> parseStepInput(order = 0, text = row.text, timerMinutesInput = row.timerMinutes) }
            .mapIndexed { index, step -> step.copy(order = index + 1) }

        appContainer.addRecipe(
            title = title.trim(),
            tags = tags,
            servings = servings,
            ingredients = ingredients,
            steps = steps,
            notes = notes.trim().ifBlank { null },
        )
    }
}
