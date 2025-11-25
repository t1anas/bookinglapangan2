package com.example.bookinglapangan

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingActivity : AppCompatActivity() {

    private lateinit var rvLapangan: RecyclerView
    private lateinit var btnBayar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // ---- Inisialisasi ----
        rvLapangan = findViewById(R.id.rvLapangan)
        btnBayar = findViewById(R.id.btnBayar)

        // ---- Dummy Data ----
        val data = listOf(
            Lapangan("Lapangan Futsal A", "Rp. 75.000/jam", "13.00-14.00", false, R.drawable.lapangan1),
            Lapangan("Lapangan Futsal A", "Rp. 75.000/jam", "15.00-16.00", true, R.drawable.lapangan1),
            Lapangan("Lapangan Futsal B", "Rp. 75.000/jam", "07.00-08.00", false, R.drawable.lapangan2),
        )

        // ---- Pasang Adapter ----
        rvLapangan.layoutManager = LinearLayoutManager(this)
        rvLapangan.adapter = LapanganAdapter(data)

        // ---- Event Bayar ----
        btnBayar.setOnClickListener {
            Toast.makeText(this, "Menuju Pembayaran...", Toast.LENGTH_SHORT).show()
            // atau langsung startActivity(Intent(this, BayarActivity::class.java))
        }
    }
}
