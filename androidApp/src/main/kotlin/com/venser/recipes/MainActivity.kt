package com.venser.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.venser.recipes.ui.RecipesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as RecipesApplication).appContainer
        setContent {
            RecipesApp(appContainer)
        }
    }
}
