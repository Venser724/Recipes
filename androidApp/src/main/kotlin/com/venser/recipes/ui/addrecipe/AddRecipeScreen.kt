package com.venser.recipes.ui.addrecipe

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venser.recipes.R
import com.venser.recipes.di.AppContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(appContainer: AppContainer, onSaved: () -> Unit, onImportedAndSaved: (Long) -> Unit) {
    val viewModel: AddRecipeViewModel = viewModel(
        factory = viewModelFactory { initializer { AddRecipeViewModel(appContainer) } },
    )
    val context = LocalContext.current
    val clipboard = LocalClipboard.current
    val coroutineScope = rememberCoroutineScope()
    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult
        coroutineScope.launch {
            val json = withContext(Dispatchers.IO) {
                runCatching {
                    context.contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readText() }
                }.getOrNull()
            }
            viewModel.importFromJson(json.orEmpty())
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Новый рецепт") }) },
        bottomBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedButton(
                        onClick = { importLauncher.launch("*/*") },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Импорт из JSON")
                    }
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, RECIPE_IMPORT_TEMPLATE)
                            }
                            context.startActivity(Intent.createChooser(intent, null))
                        },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Отправить шаблон")
                    }
                }
                OutlinedButton(
                    onClick = {
                        coroutineScope.launch {
                            val text = clipboard.getClipEntry()?.clipData?.getItemAt(0)
                                ?.coerceToText(context)?.toString().orEmpty()
                            val recipeId = viewModel.importFromClipboardAndSave(text)
                            if (recipeId != null) onImportedAndSaved(recipeId)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    Text("Вставить из буфера")
                }
                viewModel.importError?.let { error ->
                    Text(
                        error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
                Button(
                    onClick = {
                        viewModel.save()
                        onSaved()
                    },
                    enabled = viewModel.title.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    Text("Сохранить")
                }
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            item {
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it },
                    label = { Text("Название") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                )
            }
            item {
                OutlinedTextField(
                    value = viewModel.tagsInput,
                    onValueChange = { viewModel.tagsInput = it },
                    label = { Text("Теги (через запятую)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                )
            }
            item {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { viewModel.decrementServings() }) {
                        Text("−", style = MaterialTheme.typography.titleLarge)
                    }
                    Text("${viewModel.servings}", style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = { viewModel.incrementServings() }) {
                        Text("+", style = MaterialTheme.typography.titleLarge)
                    }
                    Text("порций", style = MaterialTheme.typography.bodyMedium)
                }
            }
            item {
                OutlinedTextField(
                    value = viewModel.notes,
                    onValueChange = { viewModel.notes = it },
                    label = { Text("Заметки (необязательно)") },
                    minLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                )
            }
            item {
                Text(
                    "Ингредиенты",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                )
            }
            itemsIndexed(viewModel.ingredientRows) { _, row ->
                IngredientRowEditor(
                    row = row,
                    onRemove = { viewModel.removeIngredientRow(row) },
                )
            }
            item {
                TextButton(onClick = { viewModel.addIngredientRow() }) {
                    Text("+ Добавить ингредиент")
                }
            }
            item {
                Text(
                    "Шаги",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                )
            }
            itemsIndexed(viewModel.stepRows) { index, row ->
                StepRowEditor(
                    order = index + 1,
                    row = row,
                    onRemove = { viewModel.removeStepRow(row) },
                )
            }
            item {
                TextButton(onClick = { viewModel.addStepRow() }) {
                    Text("+ Добавить шаг")
                }
            }
        }
    }
}

@Composable
private fun IngredientRowEditor(row: IngredientFormRow, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = row.name,
            onValueChange = { row.name = it },
            label = { Text("Название") },
            modifier = Modifier.weight(2f),
        )
        OutlinedTextField(
            value = row.amount,
            onValueChange = { row.amount = it },
            label = { Text("Кол-во") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.weight(1f),
        )
        OutlinedTextField(
            value = row.unit,
            onValueChange = { row.unit = it },
            label = { Text("Ед.") },
            modifier = Modifier.weight(1f),
        )
        IconButton(onClick = onRemove) {
            Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "Удалить ингредиент")
        }
    }
}

@Composable
private fun StepRowEditor(order: Int, row: StepFormRow, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        OutlinedTextField(
            value = row.text,
            onValueChange = { row.text = it },
            label = { Text("Шаг $order") },
            minLines = 2,
            modifier = Modifier.weight(3f),
        )
        OutlinedTextField(
            value = row.timerMinutes,
            onValueChange = { row.timerMinutes = it },
            label = { Text("Мин.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
        )
        IconButton(onClick = onRemove) {
            Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "Удалить шаг")
        }
    }
}
