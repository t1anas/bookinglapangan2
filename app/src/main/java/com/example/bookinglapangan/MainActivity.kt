package com.example.bookinglapangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.bookinglapangan.utils.GoogleAuthManager

class MainActivity : AppCompatActivity() {

    private lateinit var googleAuthManager: GoogleAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleAuthManager = GoogleAuthManager(this)

        // Cek apakah user sudah login
        if (!googleAuthManager.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // --- Ambil ID dari XML ---
        val txtNamaLapangan = findViewById<TextView>(R.id.txtNamaLapangan)
        val txtRating = findViewById<TextView>(R.id.txtRating)
        val txtJadwal = findViewById<TextView>(R.id.txtJadwal)

        val btnNotification = findViewById<ImageView>(R.id.btnNotification)
        val btnProfile = findViewById<ImageView>(R.id.btnProfile)

        val btnJadwal = findViewById<Button>(R.id.btnJadwal)
        val btnRiwayat = findViewById<Button>(R.id.btnRiwayat)

        // ---- Event Listener ----

        // Buka Notifikasi
        btnNotification.setOnClickListener {
            // Pindah ke ProfileActivity dengan flag untuk buka fragment notifikasi
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("OPEN_NOTIFICATION", true)
            startActivity(intent)
        }

        // Navigasi ke ProfileActivity
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        btnJadwal.setOnClickListener {
            Toast.makeText(this, "Silahkan Memilih jadwal", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, JadwalActivity::class.java))
        }

        btnRiwayat.setOnClickListener {
            Toast.makeText(this, "Riwayat Pemesanan dibuka", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        // --- Contoh update data dinamis (opsional) ---
        txtNamaLapangan.text = "Griya Futsal Gravite"
        txtRating.text = "⭐ 4.3 - Kota Madiun"
        txtJadwal.text = "Senin - Minggu\n07.00 - 23.00 WIB"
    }
}