package com.venser.recipes.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.venser.recipes.di.AppContainer
import com.venser.recipes.ui.addrecipe.AddRecipeScreen
import com.venser.recipes.ui.help.HelpScreen
import com.venser.recipes.ui.recipedetail.RecipeDetailScreen
import com.venser.recipes.ui.recipelist.RecipeListScreen
import com.venser.recipes.ui.shoppinglist.ShoppingListScreen
import com.venser.recipes.ui.theme.RecipesTheme

private const val ROUTE_RECIPE_LIST = "recipeList"
private const val ROUTE_SHOPPING_LIST = "shoppingList"
private const val ROUTE_ADD_RECIPE = "addRecipe"
private const val ROUTE_RECIPE_DETAIL = "recipeDetail/{recipeId}"
private const val ROUTE_HELP = "help"

private fun recipeDetailRoute(recipeId: Long) = "recipeDetail/$recipeId"

@Composable
fun RecipesApp(appContainer: AppContainer) {
    RecipesTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ROUTE_RECIPE_LIST) {
                composable(ROUTE_RECIPE_LIST) {
                    RecipeListScreen(
                        appContainer = appContainer,
                        onOpenRecipe = { recipeId -> navController.navigate(recipeDetailRoute(recipeId)) },
                        onOpenShoppingList = { navController.navigate(ROUTE_SHOPPING_LIST) },
                        onAddRecipe = { navController.navigate(ROUTE_ADD_RECIPE) },
                        onOpenHelp = { navController.navigate(ROUTE_HELP) },
                    )
                }
                composable(ROUTE_HELP) {
                    HelpScreen()
                }
                composable(ROUTE_RECIPE_DETAIL) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")?.toLongOrNull()
                    if (recipeId != null) {
                        RecipeDetailScreen(
                            appContainer = appContainer,
                            recipeId = recipeId,
                            onDeleted = { navController.popBackStack() },
                        )
                    }
                }
                composable(ROUTE_SHOPPING_LIST) {
                    ShoppingListScreen(appContainer = appContainer)
                }
                composable(ROUTE_ADD_RECIPE) {
                    AddRecipeScreen(
                        appContainer = appContainer,
                        onSaved = { navController.popBackStack() },
                        onImportedAndSaved = { recipeId ->
                            navController.navigate(recipeDetailRoute(recipeId)) {
                                popUpTo(ROUTE_ADD_RECIPE) { inclusive = true }
                            }
                        },
                    )
                }
            }
        }
    }
}
