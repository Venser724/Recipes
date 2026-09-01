package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
private data class RecipeImportPayload(
    val title: String,
    val tags: List<String> = emptyList(),
    val servings: Int,
    val ingredients: List<IngredientPayload> = emptyList(),
    val steps: List<StepPayload> = emptyList(),
    val notes: String? = null,
) {
    @Serializable
    data class IngredientPayload(val name: String, val amount: Double, val unit: String)

    @Serializable
    data class StepPayload(val text: String, val timerSeconds: Int? = null)
}

data class ParsedRecipe(
    val title: String,
    val tags: List<String>,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val notes: String?,
)

private val recipeJson = Json {
    ignoreUnknownKeys = true
    allowTrailingComma = true
}

private fun extractJsonObject(raw: String): String {
    val start = raw.indexOf('{')
    val end = raw.lastIndexOf('}')
    return if (start != -1 && end != -1 && end > start) raw.substring(start, end + 1) else raw
}

fun parseRecipeJson(json: String): Result<ParsedRecipe> = runCatching {
    val payload = recipeJson.decodeFromString<RecipeImportPayload>(extractJsonObject(json))
    require(payload.title.isNotBlank()) { "Название рецепта не может быть пустым" }
    require(payload.servings > 0) { "Количество порций должно быть больше нуля" }

    ParsedRecipe(
        title = payload.title,
        tags = payload.tags,
        servings = payload.servings,
        ingredients = payload.ingredients.map { Ingredient(name = it.name, amount = it.amount, unit = it.unit) },
        steps = payload.steps.mapIndexed { index, step ->
            Step(order = index + 1, text = step.text, timerSeconds = step.timerSeconds)
        },
        notes = payload.notes,
    )
}
