package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinglapangan.R

class RiwayatFragment : Fragment() {

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var adapter: RiwayatAdapter
    private lateinit var btnNotification: ImageView
    private lateinit var btnProfile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind view dari layout fragment
        rvRiwayat = view.findViewById(R.id.rvRiwayat)
        btnNotification = view.findViewById(R.id.btnNotification)
        btnProfile = view.findViewById(R.id.btnProfile)

        // RecyclerView
        rvRiwayat.layoutManager = LinearLayoutManager(requireContext())

        // Event klik notifikasi
        btnNotification.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.putExtra("OPEN_NOTIFICATION", true)
            startActivity(intent)
        }

        // Event klik profile
        btnProfile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        // Dummy data Riwayat
        val dataRiwayat = listOf(
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                jam = "09:00 - 10:00",
                harga = "Rp. 75.000",
                status = "Selesai",
                gambar = R.drawable.lapangan1
            ),
            RiwayatModel(
                nama = "Lapangan Futsal A",
                tanggal = "14 Nov 2025",
                jam = "14:00 - 15:00",
                harga = "Rp. 75.000",
                status = "Berlangsung",
                gambar = R.drawable.lapangan1
            ),
            RiwayatModel(
                nama = "Lapangan Futsal B",
                tanggal = "15 Nov 2025",
                jam = "10:00 - 11:00",
                harga = "Rp. 85.000",
                status = "Akan datang",
                gambar = R.drawable.lapangan1
            )
        )

        adapter = RiwayatAdapter(dataRiwayat)
        rvRiwayat.adapter = adapter
    }
}
