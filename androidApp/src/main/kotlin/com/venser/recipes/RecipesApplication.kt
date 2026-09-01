package com.venser.recipes

import android.app.Application
import com.venser.recipes.data.DatabaseDriverFactory
import com.venser.recipes.di.AppContainer

class RecipesApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(DatabaseDriverFactory(this))
    }
}
