package com.example.bookinglapangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- Ambil ID dari XML ---
        val txtNamaLapangan = findViewById<TextView>(R.id.txtNamaLapangan)
        val txtRating = findViewById<TextView>(R.id.txtRating)
        val txtJadwal = findViewById<TextView>(R.id.txtJadwal)

        val iconNotification = findViewById<TextView>(R.id.iconNotification)
        val iconProfile = findViewById<TextView>(R.id.iconProfile)

        val btnPesan = findViewById<Button>(R.id.btnPesan)
        val btnRiwayat = findViewById<Button>(R.id.btnRiwayat)


        // ---- Event Listener ----

        iconNotification.setOnClickListener {
            Toast.makeText(this, "Notifikasi dibuka", Toast.LENGTH_SHORT).show()
        }

        iconProfile.setOnClickListener {
            Toast.makeText(this, "Profil dibuka", Toast.LENGTH_SHORT).show()
        }

        btnPesan.setOnClickListener {
            Toast.makeText(this, "Menu Pemesanan dibuka", Toast.LENGTH_SHORT).show()

            // --- INI YANG BENER: buka BookingActivity ---
            startActivity(Intent(this, BookingActivity::class.java))
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
