package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bookinglapangan.utils.GoogleAuthManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var googleAuthManager: GoogleAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cek login
        googleAuthManager = GoogleAuthManager(this)
        if (!googleAuthManager.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Ambil NavController dengan cara aman
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as? NavHostFragment
        val navController = navHostFragment?.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Hubungkan BottomNavigationView jika navController tidak null
        navController?.let {
            bottomNav.setupWithNavController(it)
        }
    }
}
