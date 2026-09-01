package com.venser.recipes.ui.help

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Как пользоваться") }) }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            item {
                HelpSection(
                    title = "Импорт рецепта через нейронку",
                    steps = listOf(
                        "На экране «Добавить рецепт» нажмите «Отправить шаблон» — откроется системное меню, выберите ИИ-приложение (ChatGPT, Gemini, Claude и т.д.).",
                        "В открывшемся чате допишите после «Recipe to convert:» сам рецепт — текстом или скриншотом — и отправьте.",
                        "Скопируйте ответ нейронки — это будет JSON.",
                        "Вернитесь в приложение, снова откройте «Добавить рецепт» и нажмите «Вставить из буфера» — рецепт сразу сохранится и откроется.",
                    ),
                    note = "Также можно сохранить ответ в .json-файл и импортировать через «Импорт из JSON» — тогда форма заполнится для проверки перед сохранением, а не сохранится сразу.",
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
            item {
                HelpSection(
                    title = "Ручное добавление рецепта",
                    steps = listOf(
                        "На экране списка рецептов нажмите «+».",
                        "Заполните название, теги через запятую, количество порций и заметки (необязательно).",
                        "Добавьте ингредиенты — название, количество, единицу измерения; «+ Добавить ингредиент» — ещё одна строка.",
                        "Добавьте шаги — текст и, если нужно, таймер в минутах; «+ Добавить шаг» — ещё один шаг.",
                        "Нажмите «Сохранить».",
                    ),
                    modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun HelpSection(
    title: String,
    steps: List<String>,
    modifier: Modifier = Modifier,
    note: String? = null,
) {
    Column(modifier = modifier) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        steps.forEachIndexed { index, step ->
            Text(
                "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
        if (note != null) {
            Text(
                note,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}
