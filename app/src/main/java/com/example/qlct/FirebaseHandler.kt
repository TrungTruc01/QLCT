package com.example.qlct

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object FirebaseHandler {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val expensesRef: DatabaseReference = database.reference.child("expenses")
    private val transactionsRef: DatabaseReference = database.reference.child("transactions")

    // Hàm để lấy danh sách chi tiêu từ Firebase
    fun getExpenses(callback: (List<Expense>) -> Unit) {
        expensesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val expenses = mutableListOf<Expense>()
                for (expenseSnapshot in snapshot.children) {
                    val expense = expenseSnapshot.getValue(Expense::class.java)
                    expense?.let { expenses.add(it) }
                }
                callback(expenses)  // Trả về danh sách chi tiêu
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu có
            }
        })
    }

    // Hàm để thêm chi tiêu vào Firebase
    fun addExpense(expense: Expense) {
        val expenseId = expensesRef.push().key
        if (expenseId != null) {
            expensesRef.child(expenseId).setValue(expense)
                .addOnSuccessListener {
                    // Xử lý thành công
                }
                .addOnFailureListener { exception ->
                    // Xử lý lỗi nếu có
                }
        }
    }

    // Hàm để lấy danh sách giao dịch từ Firebase
    fun getTransactions(callback: (List<Transaction>) -> Unit) {
        transactionsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val transactions = mutableListOf<Transaction>()
                for (transactionSnapshot in snapshot.children) {
                    val transaction = transactionSnapshot.getValue(Transaction::class.java)
                    transaction?.let { transactions.add(it) }
                }
                callback(transactions)  // Trả về danh sách giao dịch
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu có
            }
        })
    }

    // Hàm để thêm giao dịch vào Firebase
    fun addTransaction(transaction: Transaction) {
        val transactionId = transactionsRef.push().key
        if (transactionId != null) {
            transactionsRef.child(transactionId).setValue(transaction)
                .addOnSuccessListener {
                    // Xử lý thành công
                }
                .addOnFailureListener { exception ->
                    // Xử lý lỗi nếu có
                }
        }
    }
}
