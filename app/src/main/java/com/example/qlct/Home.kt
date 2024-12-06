package com.example.qlct

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.example.qlct.databinding.FragmentHomeBinding

class Home : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // Set up the bot icon (if needed for functionality or just UI)
        setupBotIcon()

        // Set up the Pie Chart
        setupPieChart()

        // Set up RecyclerView for expenses
        setupRecyclerView()

        // Set up the balance info (total balance, expenses, income)
        setupBalanceInfo()

        // Set up the ChipGroup to handle time filters (all-time, yearly, monthly)
        setupChipGroup()
    }

    // Setup for the Bot Icon (if needed for functionality)
    private fun setupBotIcon() {
        binding.botIcon.setOnClickListener {
            // Handle bot icon click if needed
        }
    }

    // Setup RecyclerView to display the list of expenses
    private fun setupRecyclerView() {
        val expenseList = listOf(
            Expense(R.drawable.ic_food, "Ăn uống", "₫70,000", 70),
            Expense(R.drawable.ic_shopping, "Mua sắm", "₫20,000", 20)
        )
        binding.expenseList.layoutManager = LinearLayoutManager(requireContext())
        binding.expenseList.adapter = ExpenseAdapter(expenseList)
    }

    // Setup Pie Chart to visualize expense categories
    private fun setupPieChart() {
        val entries = listOf(
            PieEntry(70f, "Ăn uống"),
            PieEntry(20f, "Mua sắm")
        )
        val dataSet = PieDataSet(entries, "Danh mục chi tiêu")
        dataSet.colors = listOf(Color.parseColor("#FF6B6B"), Color.parseColor("#4CAF50"))
        dataSet.valueTextSize = 12f // Text size on Pie chart

        val pieData = PieData(dataSet)
        binding.pieChart.data = pieData
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setHoleColor(Color.TRANSPARENT)
        binding.pieChart.invalidate() // Refresh the Pie chart
    }

    // Setup the balance information: Total Expenses, Income, and Balance
    @SuppressLint("SetTextI18n")
    private fun setupBalanceInfo() {
        val totalExpenses = 90000 // Total expenses
        val totalIncome = 0       // Total income
        val totalBalance = totalIncome - totalExpenses

        binding.expenseAmount.text = "₫${formatCurrency(totalExpenses)}"
        binding.incomeAmount.text = "₫${formatCurrency(totalIncome)}"
        binding.totalBalance.text = "₫${formatCurrency(totalBalance)}"
    }

    // Format currency to display with comma separation
    @SuppressLint("DefaultLocale")
    private fun formatCurrency(amount: Int): String {
        return String.format("%,d", amount).replace(',', '.')
    }

    // Setup ChipGroup to handle time-based filtering (All, Yearly, Monthly)
    private fun setupChipGroup() {
        binding.timeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chip_all_time -> {
                    // Handle "Mọi thời gian" chip selected
                }
                R.id.chip_yearly -> {
                    // Handle "Hàng năm" chip selected
                }
                R.id.chip_monthly -> {
                    // Handle "Hàng tháng" chip selected
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up memory
    }
}
