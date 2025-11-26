package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView // Tambah Import
import android.widget.Toast // Tambah Import
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var adapter: RiwayatAdapter
    private lateinit var btnNotification: ImageView // Deklarasi ImageView
    private lateinit var btnProfile: ImageView // Deklarasi ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        rvRiwayat = findViewById(R.id.rvRiwayat)
        rvRiwayat.layoutManager = LinearLayoutManager(this)

        // Inisialisasi ikon
        btnNotification = findViewById(R.id.btnNotification)
        btnProfile = findViewById(R.id.btnProfile)

        // Event Listener untuk Notifikasi
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Notifikasi Riwayat dibuka", Toast.LENGTH_SHORT).show()
        }

        // Event Listener untuk Profile (Sama seperti di MainActivity)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

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