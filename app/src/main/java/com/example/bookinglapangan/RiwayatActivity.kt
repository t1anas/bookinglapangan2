package com.example.bookinglapangan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_riwayat)

        // safe inset (WAJIB karena file lain juga memakai ini)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rv = findViewById<RecyclerView>(R.id.rvRiwayat)
        rv.layoutManager = LinearLayoutManager(this)

        val data = listOf(
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                waktu = "09.00 - 10.00",
                harga = "Rp. 75.000/jam",
                status = "Selesai",
                gambar = R.drawable.lapangan_sample
            ),
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                waktu = "10.00 - 11.00",
                harga = "Rp. 75.000/jam",
                status = "Berlangsung",
                gambar = R.drawable.lapangan_sample
            ),
            RiwayatModel(
                nama = "Lapangan Futsal B",
                tanggal = "19 Nov 2025",
                waktu = "13.00 - 14.00",
                harga = "Rp. 65.000/jam",
                status = "Akan Datang",
                gambar = R.drawable.lapangan_sample
            )
        )

        rv.adapter = RiwayatAdapter(data) {
            Toast.makeText(this, "Klik: ${it.nama}", Toast.LENGTH_SHORT).show()
        }
    }
}
