plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.sqldelight)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    android {
        namespace = "com.venser.recipes.shared"
        compileSdk = 36
        minSdk = 28

        withHostTestBuilder {}.configure {}
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

sqldelight {
    databases {
        create("RecipesDatabase") {
            packageName.set("com.venser.recipes.db")
        }
    }
}
