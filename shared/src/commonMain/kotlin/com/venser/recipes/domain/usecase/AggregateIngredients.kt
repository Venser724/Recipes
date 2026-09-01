package com.venser.recipes.domain.usecase

import com.venser.recipes.domain.model.AggregatedIngredient
import com.venser.recipes.domain.model.Ingredient

fun aggregateIngredients(ingredients: List<Ingredient>): List<AggregatedIngredient> =
    ingredients
        .groupBy { it.name to it.unit }
        .map { (nameAndUnit, group) ->
            val (name, unit) = nameAndUnit
            AggregatedIngredient(name = name, amount = group.sumOf { it.amount }, unit = unit)
        }
        .sortedWith(compareBy({ it.name }, { it.unit }))
