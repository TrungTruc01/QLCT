package com.example.qlct

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qlct.databinding.FragmentWalletBinding

class Wallet : Fragment(R.layout.fragment_wallet) {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!
    private lateinit var transactionAdapter: TransactionAdapter
    private val transactionList = mutableListOf<Transaction>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWalletBinding.bind(view)

        setupRecyclerView()
        loadTransactionsFromFirebase()

        // Setup Floating Action Button
        binding.fabAddTransaction.setOnClickListener {
            // Handle "Add Transaction" button click here (e.g., navigate to a new screen)
        }
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(transactionList)
        binding.walletTransactionList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionAdapter
        }
    }

    private fun loadTransactionsFromFirebase() {
        FirebaseHandler.getTransactions { transactions ->
            transactionList.clear()
            transactionList.addAll(transactions)
            transactionAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
