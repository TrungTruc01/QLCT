package com.example.qlct

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thêm sự kiện chọn item trong BottomNavigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    loadFragment(Home())  // Chuyển sang trang chủ
                    true
                }
                R.id.menu_lich_su -> {
                    loadFragment(Wallet())  // Chuyển sang lịch sử giao dịch
                    true
                }
                else -> false
            }
        }

        // Mặc định khi vào ứng dụng sẽ hiển thị màn hình Home
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.menu_home
        }
    }

    // Hàm để load Fragment
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
