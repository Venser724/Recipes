package com.venser.recipes.ui

fun formatAmount(amount: Double): String =
    if (amount == amount.toLong().toDouble()) amount.toLong().toString() else amount.toString()

fun formatDuration(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return buildString {
        if (minutes > 0) append("$minutes мин ")
        if (seconds > 0 || minutes == 0) append("$seconds сек")
    }.trim()
}
