package com.venser.recipes.ui.recipelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venser.recipes.R
import com.venser.recipes.di.AppContainer
import com.venser.recipes.domain.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    appContainer: AppContainer,
    onOpenRecipe: (Long) -> Unit,
    onOpenShoppingList: () -> Unit,
) {
    val viewModel: RecipeListViewModel = viewModel(
        factory = viewModelFactory { initializer { RecipeListViewModel(appContainer) } },
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Рецепты") }) },
        bottomBar = {
            if (viewModel.selectedRecipeIds.isNotEmpty()) {
                Button(
                    onClick = {
                        viewModel.generateShoppingList()
                        onOpenShoppingList()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Список покупок (${viewModel.selectedRecipeIds.size})")
                }
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            items(viewModel.recipes, key = { it.id }) { recipe ->
                RecipeRow(
                    recipe = recipe,
                    isSelected = recipe.id in viewModel.selectedRecipeIds,
                    onSelectToggle = { viewModel.toggleSelected(recipe.id) },
                    onOpen = { onOpenRecipe(recipe.id) },
                )
            }
        }
    }
}

@Composable
private fun RecipeRow(
    recipe: Recipe,
    isSelected: Boolean,
    onSelectToggle: () -> Unit,
    onOpen: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onOpen)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onSelectToggle) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = if (isSelected) "Убрать из списка покупок" else "Добавить в список покупок",
                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(recipe.title)
            Text("${recipe.category} · ${recipe.servings} порц.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
