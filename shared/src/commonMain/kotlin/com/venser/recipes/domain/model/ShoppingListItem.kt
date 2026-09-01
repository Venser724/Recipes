package com.venser.recipes.domain.model

data class ShoppingListItem(
    val id: Long,
    val name: String,
    val amount: Double,
    val unit: String,
    val checked: Boolean,
)
