package com.venser.recipes.data

import com.venser.recipes.db.Ingredient as IngredientRow
import com.venser.recipes.db.Recipe as RecipeRow
import com.venser.recipes.db.RecipesDatabase
import com.venser.recipes.db.Step as StepRow
import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Recipe
import com.venser.recipes.domain.model.Step

class RecipeRepository(private val database: RecipesDatabase) {

    fun getRecipes(): List<Recipe> =
        database.recipesQueries.selectAllRecipes().executeAsList().map(::toRecipe)

    fun getRecipe(id: Long): Recipe? =
        database.recipesQueries.selectRecipeById(id).executeAsOneOrNull()?.let(::toRecipe)

    fun addRecipe(
        title: String,
        category: String,
        servings: Int,
        ingredients: List<Ingredient>,
        steps: List<Step>,
    ) {
        database.transaction {
            database.recipesQueries.insertRecipe(title = title, category = category, servings = servings.toLong())
            val recipeId = database.recipesQueries.lastInsertRowId().executeAsOne()
            ingredients.forEach { ingredient ->
                database.recipesQueries.insertIngredient(
                    recipeId = recipeId,
                    name = ingredient.name,
                    amount = ingredient.amount,
                    unit = ingredient.unit,
                )
            }
            steps.forEach { step ->
                database.recipesQueries.insertStep(
                    recipeId = recipeId,
                    stepOrder = step.order.toLong(),
                    text = step.text,
                    timerSeconds = step.timerSeconds?.toLong(),
                )
            }
        }
    }

    private fun toRecipe(row: RecipeRow): Recipe {
        val ingredients = database.recipesQueries.selectIngredientsForRecipe(row.id)
            .executeAsList()
            .map(::toIngredient)
        val steps = database.recipesQueries.selectStepsForRecipe(row.id)
            .executeAsList()
            .map(::toStep)
        return Recipe(
            id = row.id,
            title = row.title,
            category = row.category,
            servings = row.servings.toInt(),
            ingredients = ingredients,
            steps = steps,
        )
    }

    private fun toIngredient(row: IngredientRow) =
        Ingredient(name = row.name, amount = row.amount, unit = row.unit)

    private fun toStep(row: StepRow) =
        Step(order = row.stepOrder.toInt(), text = row.text, timerSeconds = row.timerSeconds?.toInt())
}
