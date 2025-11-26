package com.example.bookinglapangan

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class BookingActivity : AppCompatActivity() {

    private lateinit var rvLapangan: RecyclerView
    private lateinit var btnBayar: Button
    private lateinit var tvDayName: TextView
    private lateinit var tvDate: TextView
    private lateinit var btnNotif: TextView
    private lateinit var btnProfile: TextView

    private lateinit var adapter: LapanganAdapter
    private var selectedLapangan: Lapangan? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // ---- Inisialisasi Views ----
        tvDayName = findViewById(R.id.tvDayName)
        tvDate = findViewById(R.id.tvDate)
        rvLapangan = findViewById(R.id.rvLapangan)
        btnBayar = findViewById(R.id.btnBayar)
        btnNotif = findViewById(R.id.btnNotif)
        btnProfile = findViewById(R.id.btnProfile)

        // ---- Set tanggal dari JadwalActivity atau default hari ini ----
        setCurrentDate()

        // ---- Dummy Data ----
        val data = listOf(
            Lapangan("Lapangan Futsal A", "Rp. 75.000/jam", "13.00-14.00", false, R.drawable.lapangan1),
            Lapangan("Lapangan Futsal A", "Rp. 75.000/jam", "15.00-16.00", true, R.drawable.lapangan1),
            Lapangan("Lapangan Futsal B", "Rp. 75.000/jam", "07.00-08.00", false, R.drawable.lapangan2),
        )

        // ---- Pasang Adapter dengan callback selection ----
        adapter = LapanganAdapter(data) { lapangan ->
            // Callback ketika item diklik
            selectedLapangan = lapangan
            updateBayarButton()
        }

        rvLapangan.layoutManager = LinearLayoutManager(this)
        rvLapangan.adapter = adapter

        // ---- Event Bayar ----
        btnBayar.setOnClickListener {
            if (selectedLapangan != null) {
                if (selectedLapangan!!.isBooked) {
                    Toast.makeText(this, "Lapangan sudah dibooking!", Toast.LENGTH_SHORT).show()
                } else {
                    // Kirim data ke BayarActivity
                    val intent = Intent(this, BayarActivity::class.java)
                    intent.putExtra("NAMA_LAPANGAN", selectedLapangan!!.nama)
                    intent.putExtra("HARGA", selectedLapangan!!.harga)
                    intent.putExtra("JAM", selectedLapangan!!.jam)
                    intent.putExtra("TANGGAL", tvDate.text.toString())
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Pilih lapangan terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }

        // ---- Event Notif ----
        btnNotif.setOnClickListener {
            Toast.makeText(this, "Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // ---- Event Profile ----
        btnProfile.setOnClickListener {
            Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateBayarButton() {
        // Update tampilan button berdasarkan status seleksi
        if (selectedLapangan != null) {
            if (selectedLapangan!!.isBooked) {
                btnBayar.text = "Lapangan Sudah Dibooking"
                btnBayar.isEnabled = false
                btnBayar.alpha = 0.5f
            } else {
                btnBayar.text = "Lanjut ke Pembayaran"
                btnBayar.isEnabled = true
                btnBayar.alpha = 1.0f
            }
        } else {
            btnBayar.text = "Pilih Lapangan"
            btnBayar.isEnabled = false
            btnBayar.alpha = 0.5f
        }
    }

    private fun setCurrentDate() {
        // Ambil data tanggal dari JadwalActivity (jika ada)
        val selectedDate = intent.getStringExtra("SELECTED_DATE")
        val selectedDateMillis = intent.getLongExtra("SELECTED_DATE_MILLIS", 0L)

        val calendar = Calendar.getInstance()

        // Jika ada tanggal yang dipilih dari JadwalActivity, gunakan itu
        if (selectedDateMillis != 0L) {
            calendar.timeInMillis = selectedDateMillis
        }

        // Format nama hari (Senin, Selasa, dst)
        val dayFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
        val dayName = dayFormat.format(calendar.time)

        // Format tanggal lengkap (26 November 2025)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        val dateString = dateFormat.format(calendar.time)

        // Set ke TextView
        tvDayName.text = dayName
        tvDate.text = dateString
    }
}