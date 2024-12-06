package com.example.qlct

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qlct.databinding.ItemExpenseBinding

class ExpenseAdapter(private val expenseList: MutableList<Expense>) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // ViewHolder giữ các tham chiếu đến view
    inner class ExpenseViewHolder(val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        // Inflate layout item cho mỗi chi tiêu
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
        loadExpensesFromFirebase()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.binding.apply {
            // Cập nhật icon, tên danh mục và số tiền
            expenseIcon.setImageResource(expense.icon) // Sử dụng expense.icon từ đối tượng Expense
            expenseName.text = expense.category // Sử dụng expense.category từ đối tượng Expense
            expenseAmount.text = expense.amount // Sử dụng expense.amount từ đối tượng Expense

            // Cập nhật giá trị cho ProgressBar và tỷ lệ phần trăm
            expenseProgress.progress = expense.progress // Sử dụng expense.progress để cập nhật thanh tiến trình
            expensePercentage.text = "${expense.progress}%" // Hiển thị tỷ lệ phần trăm
        }
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    // Hàm tải dữ liệu chi tiêu từ Firebase
    fun loadExpensesFromFirebase() {
        FirebaseHandler.getExpenses { expenses ->
            expenseList.clear() // Xóa tất cả các mục cũ
            expenseList.addAll(expenses)  // Thêm tất cả các mục mới từ Firebase vào danh sách
            notifyDataSetChanged()  // Cập nhật RecyclerView
        }
    }
}
