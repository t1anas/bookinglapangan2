package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookinglapangan.utils.GoogleAuthManager
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    private lateinit var googleAuthManager: GoogleAuthManager

    // Launcher untuk Google Sign-In
    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            googleAuthManager.handleSignInResult(
                data = result.data,
                onSuccess = { user ->
                    Toast.makeText(
                        this,
                        "Selamat datang, ${user.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Pindah ke MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi GoogleAuthManager
        googleAuthManager = GoogleAuthManager(this)

        // Cek apakah user sudah login
        if (googleAuthManager.isUserLoggedIn()) {
            // Langsung ke MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGoogle = findViewById<MaterialButton>(R.id.btnGoogle)

        btnGoogle.setOnClickListener {
            // Launch Google Sign-In
            val signInIntent = googleAuthManager.getSignInIntent()
            signInLauncher.launch(signInIntent)
        }
    }
}