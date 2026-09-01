package com.venser.recipes.domain.model

data class Step(
    val order: Int,
    val text: String,
    val timerSeconds: Int?,
)
