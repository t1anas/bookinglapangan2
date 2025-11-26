package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookinglapangan.utils.GoogleAuthManager
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var googleAuthManager: GoogleAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        googleAuthManager = GoogleAuthManager(this)

        // Initialize views
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnNotification = findViewById<ImageView>(R.id.btnNotification)
        val imgProfile = findViewById<CircleImageView>(R.id.imgProfile)
        val txtUserName = findViewById<TextView>(R.id.txtUserName)
        val txtUserPhone = findViewById<TextView>(R.id.txtUserPhone)
        val btnKetentuan = findViewById<LinearLayout>(R.id.btnKetentuan)
        val btnHubungi = findViewById<LinearLayout>(R.id.btnHubungi)
        val btnLogout = findViewById<LinearLayout>(R.id.btnLogout)

        // Load user data
        loadUserData(imgProfile, txtUserName, txtUserPhone)

        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Notification button
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // Ketentuan Pengguna
        btnKetentuan.setOnClickListener {
            Toast.makeText(this, "Ketentuan Pengguna", Toast.LENGTH_SHORT).show()
            // Anda bisa membuka activity baru untuk menampilkan ketentuan
        }

        // Hubungi Kami
        btnHubungi.setOnClickListener {
            Toast.makeText(this, "Hubungi Kami", Toast.LENGTH_SHORT).show()
            // Anda bisa membuka intent untuk telepon atau WhatsApp
        }

        // Logout
        btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun loadUserData(
        imgProfile: CircleImageView,
        txtUserName: TextView,
        txtUserPhone: TextView
    ) {
        val currentUser = googleAuthManager.getCurrentUser()

        if (currentUser != null) {
            // Set nama
            txtUserName.text = currentUser.displayName ?: "User"

            // Set email atau phone
            txtUserPhone.text = currentUser.email ?: currentUser.phoneNumber ?: "-"

            // Load foto profil
            currentUser.photoUrl?.let { photoUrl ->
                Glide.with(this)
                    .load(photoUrl)
                    .placeholder(R.drawable.ic_user_placeholder)
                    .error(R.drawable.ic_user_placeholder)
                    .into(imgProfile)
            }
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                performLogout()
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun performLogout() {
        googleAuthManager.signOut {
            Toast.makeText(this, "Berhasil logout", Toast.LENGTH_SHORT).show()

            // Kembali ke LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}