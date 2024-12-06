package com.example.qlct

data class Expense(
    val icon: Int,        // Resource ID for the icon (e.g., food, shopping)
    val category: String, // The category of the expense (e.g., "Ăn uống")
    val amount: String,   // The amount of the expense (e.g., "₫70,000")
    val progress: Int        // Numeric value of the expense for calculations (e.g., 70)
)
