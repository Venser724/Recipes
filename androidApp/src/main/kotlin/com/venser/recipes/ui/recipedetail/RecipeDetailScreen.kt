package com.venser.recipes.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.Ingredient
import com.venser.recipes.domain.model.Step
import com.venser.recipes.ui.formatAmount
import com.venser.recipes.ui.formatDuration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(appContainer: AppContainer, recipeId: Long) {
    val viewModel: RecipeDetailViewModel = viewModel(
        factory = viewModelFactory { initializer { RecipeDetailViewModel(appContainer, recipeId) } },
    )
    val recipe = viewModel.recipe

    Scaffold(topBar = { TopAppBar(title = { Text(recipe?.title ?: "Рецепт") }) }) { paddingValues ->
        if (recipe == null) {
            Text(
                "Рецепт не найден",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
            )
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            item {
                val subtitle = if (recipe.tags.isEmpty()) {
                    "${recipe.servings} порц."
                } else {
                    "${recipe.tags.joinToString(", ")} · ${recipe.servings} порц."
                }
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            item {
                Text(
                    "Ингредиенты",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                )
            }
            items(recipe.ingredients) { ingredient -> IngredientRow(ingredient) }
            item {
                Text(
                    "Шаги",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                )
            }
            items(recipe.steps) { step -> StepRow(step) }
        }
    }
}

@Composable
private fun IngredientRow(ingredient: Ingredient) {
    Text(
        "•  ${ingredient.name} — ${formatAmount(ingredient.amount)} ${ingredient.unit}",
        modifier = Modifier.padding(bottom = 4.dp),
    )
}

@Composable
private fun StepRow(step: Step) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text("${step.order}. ${step.text}")
        val timerSeconds = step.timerSeconds
        if (timerSeconds != null) {
            Text("⏱ ${formatDuration(timerSeconds)}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
