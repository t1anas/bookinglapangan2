package com.example.bookinglapangan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {

    private lateinit var rvRiwayat: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        rvRiwayat = findViewById(R.id.rvRiwayat)

        // Dummy data sesuai UI mockup kamu
        val data = listOf(
            RiwayatModel(
                "Lapangan Futsal A",
                "14 Nov 2025",
                "09.00–10.00",
                "Rp. 75.000/jam",
                "Selesai",
                R.drawable.lapangan1
            ),
            RiwayatModel(
                "Lapangan Futsal A",
                "14 Nov 2025",
                "10.00–11.00",
                "Rp. 75.000/jam",
                "Berlangsung",
                R.drawable.lapangan1
            ),
            RiwayatModel(
                "Lapangan Futsal B",
                "19 Nov 2025",
                "13.00–14.00",
                "Rp. 65.000/jam",
                "Akan Datang",
                R.drawable.lapangan2
            )
        )

        rvRiwayat.layoutManager = LinearLayoutManager(this)
        rvRiwayat.adapter = RiwayatAdapter(data)
    }
}
