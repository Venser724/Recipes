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

    repository.addRecipe(
        title = "Французские тосты",
        tags = listOf("Завтрак", "Сладкое"),
        servings = 4,
        ingredients = listOf(
            Ingredient(name = "Белый хлеб (лучше слегка чёрствый)", amount = 8.0, unit = "ломтик"),
            Ingredient(name = "Яйцо (крупное)", amount = 3.0, unit = "шт"),
            Ingredient(name = "Молоко", amount = 200.0, unit = "мл"),
            Ingredient(name = "Сахар", amount = 2.0, unit = "ст.л."),
            Ingredient(name = "Ванильный экстракт", amount = 1.0, unit = "ч.л."),
            Ingredient(name = "Молотая корица", amount = 1.0, unit = "ч.л."),
            Ingredient(name = "Соль", amount = 1.0, unit = "щепотка"),
            Ingredient(name = "Сливочное масло (для жарки)", amount = 2.0, unit = "ст.л."),
        ),
        steps = listOf(
            Step(order = 1, text = "Взбить яйца с молоком, сахаром, ванилью, корицей и солью до однородности", timerSeconds = null),
            Step(order = 2, text = "Растопить сливочное масло на сковороде на среднем огне", timerSeconds = null),
            Step(order = 3, text = "Обмакнуть ломтики хлеба в яичную смесь с обеих сторон", timerSeconds = 20),
            Step(order = 4, text = "Обжарить ломтики на сковороде до золотистой корочки", timerSeconds = 180),
            Step(order = 5, text = "Подавать горячими с кленовым сиропом, мёдом, ягодами или сахарной пудрой", timerSeconds = null),
        ),
        notes = "Для более насыщенного вкуса можно заменить часть молока сливками. " +
            "Хлеб лучше брать вчерашний — свежий слишком мягкий и может расползтись.",
    )

    repository.addRecipe(
        title = "Паста с тунцом и песто",
        tags = listOf("Паста", "Основное блюдо", "Морепродукты"),
        servings = 2,
        ingredients = listOf(
            Ingredient(name = "Спагетти (или другая паста)", amount = 200.0, unit = "г"),
            Ingredient(name = "Соус песто", amount = 2.0, unit = "ст.л."),
            Ingredient(name = "Тунец консервированный", amount = 1.0, unit = "банка"),
            Ingredient(name = "Красный лук", amount = 0.5, unit = "шт"),
            Ingredient(name = "Чеснок", amount = 1.0, unit = "зубчик"),
            Ingredient(name = "Соль", amount = 0.5, unit = "ч.л."),
            Ingredient(name = "Оливковое масло", amount = 2.0, unit = "ст.л."),
            Ingredient(name = "Свежий базилик", amount = 5.0, unit = "листик"),
            Ingredient(name = "Свежий укроп (рубленый)", amount = 1.0, unit = "ст.л."),
            Ingredient(name = "Смесь итальянских трав", amount = 1.0, unit = "ч.л."),
            Ingredient(name = "Томаты черри", amount = 6.0, unit = "шт"),
            Ingredient(name = "Вода после варки пасты", amount = 0.5, unit = "стакан"),
            Ingredient(name = "Тёртый пармезан (для подачи, по желанию)", amount = 30.0, unit = "г"),
        ),
        steps = listOf(
            Step(
                order = 1,
                text = "Мелко нарезать лук, раздавить и нарезать чеснок, разрезать томаты черри пополам, слить воду с тунца",
                timerSeconds = null,
            ),
            Step(order = 2, text = "Сварить пасту до состояния al dente, отложить немного воды после варки", timerSeconds = null),
            Step(order = 3, text = "Обжарить лук и чеснок на оливковом масле", timerSeconds = 180),
            Step(order = 4, text = "Добавить томаты черри и жарить до размягчения", timerSeconds = 300),
            Step(order = 5, text = "Добавить тунец и обжарить", timerSeconds = 120),
            Step(
                order = 6,
                text = "Соединить пасту с отложенной водой, базиликом, укропом и травами, активно перемешать",
                timerSeconds = 360,
            ),
            Step(order = 7, text = "Добавить песто и перемешать", timerSeconds = 180),
            Step(order = 8, text = "Подавать, посыпав тёртым пармезаном по желанию", timerSeconds = null),
        ),
        notes = "Базилик, укроп и смесь итальянских трав добавлены на этапе смешивания с пастой — " +
            "можно добавить их раньше, вместе с помидорами, если хочется иначе.",
    )
}
