package com.example.qlct

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qlct.databinding.ItemTransactionBinding

class TransactionAdapter(private var transactionList: MutableList<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.binding.apply {
            // Cập nhật thông tin giao dịch
            transactionDate.text = transaction.date
            transactionIcon.setImageResource(transaction.icon)
            transactionName.text = transaction.name
            transactionCategory.text = transaction.category
            transactionAmount.text = formatCurrency(transaction.amount)

            // Đổi màu văn bản dựa trên số tiền
            transactionAmount.setTextColor(
                if (transaction.amount < 0) {
                    // Màu đỏ cho chi tiêu
                    android.graphics.Color.parseColor("#FF6B6B")
                } else {
                    // Màu xanh cho thu nhập
                    android.graphics.Color.parseColor("#4CAF50")
                }
            )
        }
    }

    override fun getItemCount(): Int = transactionList.size

    // Định dạng tiền tệ (ví dụ: "₫50,000")
    private fun formatCurrency(amount: Float): String {
        return if (amount < 0) {
            "-₫${String.format("%,.0f", -amount)}"
        } else {
            "+₫${String.format("%,.0f", amount)}"
        }
    }

    // Hàm cập nhật dữ liệu cho adapter
    fun updateTransactions(newTransactions: List<Transaction>) {
        transactionList.clear()
        transactionList.addAll(newTransactions)
        notifyDataSetChanged() // Cập nhật lại RecyclerView
    }
}
