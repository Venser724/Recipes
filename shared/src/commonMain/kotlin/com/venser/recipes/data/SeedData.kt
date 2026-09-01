package com.venser.recipes.data

import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step

internal fun seedRecipesIfEmpty(repository: RecipeRepository) {
    if (repository.getRecipes().isNotEmpty()) return

    repository.addRecipe(
        title = "Омлет с сыром",
        tags = listOf("Завтрак"),
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
        tags = listOf("Паста", "Основное блюдо"),
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
        tags = listOf("Салат"),
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

    repository.addRecipe(
        title = "Блинчики",
        tags = listOf("Завтрак", "Сладкое"),
        servings = 4,
        ingredients = listOf(
            Ingredient(name = "Мука", amount = 200.0, unit = "г"),
            Ingredient(name = "Молоко", amount = 500.0, unit = "мл"),
            Ingredient(name = "Яйцо", amount = 2.0, unit = "шт"),
            Ingredient(name = "Сахар", amount = 30.0, unit = "г"),
            Ingredient(name = "Соль", amount = 1.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Смешать муку, молоко, яйца, сахар и соль до однородности", timerSeconds = null),
            Step(order = 2, text = "Жарить блины на раскалённой сковороде с двух сторон", timerSeconds = 120),
        ),
    )

    repository.addRecipe(
        title = "Креветки в чесночном соусе",
        tags = listOf("Морепродукты", "Основное блюдо"),
        servings = 2,
        ingredients = listOf(
            Ingredient(name = "Креветки", amount = 300.0, unit = "г"),
            Ingredient(name = "Чеснок", amount = 3.0, unit = "зубчик"),
            Ingredient(name = "Сливочное масло", amount = 30.0, unit = "г"),
            Ingredient(name = "Соль", amount = 2.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Обжарить чеснок на сливочном масле", timerSeconds = 60),
            Step(order = 2, text = "Добавить креветки и обжарить до готовности", timerSeconds = 180),
            Step(order = 3, text = "Посолить по вкусу", timerSeconds = null),
        ),
    )

    repository.addRecipe(
        title = "Куриные котлеты",
        tags = listOf("Мясо", "Основное блюдо"),
        servings = 4,
        ingredients = listOf(
            Ingredient(name = "Куриный фарш", amount = 500.0, unit = "г"),
            Ingredient(name = "Лук", amount = 1.0, unit = "шт"),
            Ingredient(name = "Яйцо", amount = 1.0, unit = "шт"),
            Ingredient(name = "Соль", amount = 4.0, unit = "г"),
        ),
        steps = listOf(
            Step(order = 1, text = "Смешать фарш с мелко нарезанным луком, яйцом и солью", timerSeconds = null),
            Step(order = 2, text = "Сформировать котлеты", timerSeconds = null),
            Step(order = 3, text = "Обжарить котлеты с двух сторон", timerSeconds = 600),
        ),
    )
}
