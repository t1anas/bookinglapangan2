package com.example.bookinglapangan

class RiwayatActivity : AppCompatActivity() {

    private lateinit var adapter: RiwayatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        val recycler = findViewById<RecyclerView>(R.id.rvRiwayat)
        recycler.layoutManager = LinearLayoutManager(this)

        val data = listOf(
            RiwayatModel(
                "Lapangan Futsal A",
                "14 Nov 2025",
                "09.00 - 10.00",
                "Rp. 75.000/jam",
                "Selesai",
                R.drawable.lapangan_sample
            ),
            RiwayatModel(
                "Lapangan Futsal A",
                "14 Nov 2025",
                "10.00 - 11.00",
                "Rp. 75.000/jam",
                "Berlangsung",
                R.drawable.lapangan_sample
            ),
            RiwayatModel(
                "Lapangan Futsal B",
                "19 Nov 2025",
                "13.00 - 14.00",
                "Rp. 65.000/jam",
                "Akan Datang",
                R.drawable.lapangan_sample
            )
        )

        adapter = RiwayatAdapter(data) { item ->
            Toast.makeText(this, "Klik: ${item.nama}", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = adapter
    }
}

}