package com.example.bookinglapangan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var adapter: RiwayatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        rvRiwayat = findViewById(R.id.rvRiwayat)
        rvRiwayat.layoutManager = LinearLayoutManager(this)

        // 🔥 Data dengan status berbeda
        val dataRiwayat = listOf(
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                jam = "09:00 - 10:00",
                harga = "Rp. 75.000",
                status = "Selesai", // Tombol TIDAK muncul
                gambar = R.drawable.lapangan1
            ),
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                jam = "14:00 - 15:00",
                harga = "Rp. 75.000",
                status = "Berlangsung", // Tombol TIDAK muncul
                gambar = R.drawable.lapangan1
            ),
            RiwayatModel(
                nama = "Lapangan Futsal B",
                tanggal = "15 Nov 2025",
                jam = "10:00 - 11:00",
                harga = "Rp. 85.000",
                status = "Akan datang", // Tombol MUNCUL
                gambar = R.drawable.lapangan1
            )
        )

        adapter = RiwayatAdapter(dataRiwayat)
        rvRiwayat.adapter = adapter
    }
}