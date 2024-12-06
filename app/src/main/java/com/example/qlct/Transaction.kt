package com.example.qlct

data class Transaction(
    val date: String = "",           // Ngày giao dịch
    val icon: Int = 0,              // Icon cho giao dịch
    val name: String = "",          // Tên giao dịch
    val category: String = "",      // Danh mục giao dịch
    val amount: Float = 0f          // Số tiền giao dịch
)
