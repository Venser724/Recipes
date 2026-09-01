# Recipes

A step-by-step recipe app for Android, built with Kotlin Multiplatform and Compose Multiplatform.

*[Русская версия ниже](#рецепты)*

## Features

- **Recipe list** with AND-filtering by category tags (a recipe must match every selected tag).
- **Recipe detail**: ingredients, numbered steps with optional timers, and free-form notes.
- **Shopping list**: pick recipes and generate a single aggregated list (matching ingredient/unit pairs across recipes are summed); tap an item to check it off.
- **Add a recipe** by hand: title, tags, servings, notes, and dynamic ingredient/step rows.
- **Delete a recipe**: a confirmation dialog on the recipe screen, or swipe left on the list for a quick one-tap delete.
- **AI-assisted import**: send a structured JSON-schema prompt to any AI app installed on the phone (via the system share sheet), then bring the response back in:
  - paste it from the clipboard — the recipe is parsed, saved, and opened immediately;
  - or import it as a `.json` file — the form is filled in for review before saving.

  The parser tolerates typical AI sloppiness (markdown code fences, stray text around the JSON, trailing commas).
- **In-app help screen** explaining both ways to add a recipe.
- **Dark theme** end to end.

## Tech stack

- Kotlin Multiplatform — `shared` module (Android target for now; the public API avoids Android-specific types so adding iOS later is low-friction) + `androidApp` module (Compose Multiplatform UI).
- [SQLDelight](https://cashapp.github.io/sqldelight/) for local persistence.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for the JSON import pipeline.
- Manual dependency injection (a plain `AppContainer`, no DI framework) — repositories stay private to the container, only use cases are exposed to the UI layer.
- MVVM presentation pattern: Compose state is read directly from the ViewModel, no `StateFlow`/reducer layer.

## Building

```bash
./gradlew :androidApp:assembleDebug
```

The debug APK lands in `androidApp/build/outputs/apk/debug/`.

---

# Рецепты

Приложение для пошаговых рецептов на Android, построенное на Kotlin Multiplatform и Compose Multiplatform.

## Возможности

- **Список рецептов** с фильтрацией по тегам (логика «И» — рецепт должен подходить под все выбранные теги одновременно).
- **Экран рецепта**: ингредиенты, пронумерованные шаги с опциональными таймерами, произвольные заметки.
- **Список покупок**: выбираете рецепты — получаете один объединённый список (совпадающие пары «ингредиент + единица измерения» суммируются между рецептами); тап по пункту отмечает его как купленный.
- **Добавление рецепта вручную**: название, теги, количество порций, заметки, динамические строки ингредиентов и шагов.
- **Удаление рецепта**: диалог подтверждения на экране рецепта, либо свайп влево в списке для быстрого удаления в один тап.
- **Импорт рецепта через нейронку**: отправляете структурированный JSON-шаблон в любое установленное на телефоне ИИ-приложение (через системное меню «Поделиться»), затем возвращаете ответ обратно:
  - вставкой из буфера обмена — рецепт сразу парсится, сохраняется и открывается;
  - либо импортом `.json`-файла — форма заполняется для проверки перед сохранением.

  Парсер устойчив к типичной небрежности нейронок (markdown-заборы вокруг JSON, лишний текст, висячие запятые).
- **Экран инструкции** в самом приложении — объясняет оба способа добавления рецепта.
- **Тёмная тема** во всём приложении.

## Стек технологий

- Kotlin Multiplatform — модуль `shared` (пока только Android-таргет; публичное API избегает Android-специфичных типов, чтобы добавление iOS в будущем было малозатратным) + модуль `androidApp` (UI на Compose Multiplatform).
- [SQLDelight](https://cashapp.github.io/sqldelight/) для локального хранения данных.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) для пайплайна импорта JSON.
- Ручное внедрение зависимостей (обычный `AppContainer`, без DI-фреймворка) — репозитории приватны внутри контейнера, наружу в UI-слой торчат только use case'ы.
- Паттерн представления MVVM: состояние Compose читается напрямую из ViewModel, без слоя `StateFlow`/редьюсера.

## Сборка

```bash
./gradlew :androidApp:assembleDebug
```

Debug-APK окажется в `androidApp/build/outputs/apk/debug/`.
