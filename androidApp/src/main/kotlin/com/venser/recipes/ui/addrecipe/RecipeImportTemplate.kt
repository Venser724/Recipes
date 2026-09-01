package com.venser.recipes.ui.addrecipe

internal val RECIPE_IMPORT_TEMPLATE = """
Convert the recipe below into a single JSON object using EXACTLY this schema. Output ONLY the JSON object — no markdown code fences, no explanations, no text before or after it, no array, no multiple objects.

{
  "title": string,
  "tags": [string],   // pick zero or more ONLY from this fixed list — never invent a new tag:
                       // ["Завтрак", "Паста", "Основное блюдо", "Салат", "Сладкое", "Морепродукты", "Мясо", "Перекус", "Гарнир"]
  "servings": integer > 0,
  "ingredients": [
    { "name": string, "amount": number, "unit": string }
  ],
  "steps": [
    { "text": string, "timerSeconds": integer }   // timerSeconds optional, omit if absent
  ],
  "notes": string   // optional
}

Rules:
- ingredients[0] and steps[0] show the exact object shape. Repeat that same shape for every additional item, just append more objects to the array, in order.
- tags: choose only from the fixed list shown above. If none of them fit the recipe, use an empty array.
- amount is always a plain unquoted number. Decimal separator is a dot, never a comma. Convert fractions to decimals (1/2 -> 0.5).
- If an ingredient has no exact quantity ("to taste", "a pinch"), set amount to 0 and put the description in "unit" (e.g. "unit": "to taste").
- servings is always a positive integer. If the source gives a range (e.g. "4-6"), round to one integer. Never leave it empty.
- timerSeconds is an integer number of seconds. Convert minutes to seconds (minutes * 60). Include it ONLY when the step explicitly states a wait/cook time; omit it otherwise — never invent a timer.
- Never insert a raw line break inside a string value; keep each string on one line.
- Escape quotation marks inside string values.
- If the text after "Recipe to convert:" already lists ingredients and steps, convert it as-is without changing amounts or steps.
- If it is just a dish name or short description with no ingredient list or steps, first write a typical recipe for it yourself, then convert that recipe into the same JSON schema.

Recipe to convert:
""".trimIndent()
