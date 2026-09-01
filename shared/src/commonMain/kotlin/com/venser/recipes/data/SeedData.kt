package com.venser.recipes.data

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step

internal fun seedRecipesIfEmpty(repository: RecipeRepository) {
    if (repository.getRecipes().isNotEmpty()) return

    repository.addRecipe(
        title = "Омлет с сыром",
        servings = 2,
        ingredients = listOf(
            Ingredient(name = "Яйцо", amount = 4.0, unit = "шт"),
            Ingredient(name = "Молоко", amount = 50.0, unit = "мл"),
            Ingredient(name = "Сыр", amount = 50.0, unit = "г"),
            Ingredient(name = "Соль", amount = 2.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Взбить яйца с молоком и солью", timerSeconds = null),
            Step(order = 2, text = "Вылить смесь на разогретую сковороду", timerSeconds = null),
            Step(order = 3, text = "Готовить под крышкой на слабом огне", timerSeconds = 240),
            Step(order = 4, text = "Посыпать тёртым сыром и снять с огня", timerSeconds = null),
        ),
    )

    repository.addRecipe(
        title = "Паста с томатным соусом",
        servings = 2,
        ingredients = listOf(
            Ingredient(name = "Спагетти", amount = 200.0, unit = "г"),
            Ingredient(name = "Томаты", amount = 400.0, unit = "г"),
            Ingredient(name = "Чеснок", amount = 2.0, unit = "зубчик"),
            Ingredient(name = "Соль", amount = 3.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Сварить спагетти в подсоленной воде", timerSeconds = 480),
            Step(order = 2, text = "Обжарить чеснок, добавить томаты", timerSeconds = 300),
            Step(order = 3, text = "Смешать соус со спагетти", timerSeconds = null),
        ),
    )

    repository.addRecipe(
        title = "Овощной салат",
        servings = 4,
        ingredients = listOf(
            Ingredient(name = "Огурец", amount = 2.0, unit = "шт"),
            Ingredient(name = "Томаты", amount = 200.0, unit = "г"),
            Ingredient(name = "Соль", amount = 2.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Нарезать овощи кубиками", timerSeconds = null),
            Step(order = 2, text = "Посолить и перемешать", timerSeconds = null),
        ),
    )
}
