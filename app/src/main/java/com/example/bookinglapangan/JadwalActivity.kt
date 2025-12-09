package com.example.bookinglapangan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.text.SimpleDateFormat
import java.util.*

class JadwalActivity : AppCompatActivity() {

    private lateinit var btnPilihTanggal: Button
    private lateinit var btnPesan: Button
    private lateinit var btnNotif: ImageView
    private lateinit var btnProfile: ImageView
    private lateinit var selectedDateCard: CardView
    private lateinit var tvSelectedDate: TextView

    private var selectedDate: String = ""
    private var selectedDateMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        // Initialize views
        initViews()

        // Setup listeners
        setupButtonListeners()

        // Set default selected date to today
        setDefaultDate()
    }

    private fun initViews() {
        btnPilihTanggal = findViewById(R.id.btnPilihTanggal)
        btnPesan = findViewById(R.id.btnPesan)
        btnNotif = findViewById(R.id.btnNotif)
        btnProfile = findViewById(R.id.btnProfile)
        selectedDateCard = findViewById(R.id.selectedDateCard)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
    }

    private fun setupButtonListeners() {
        // Button untuk buka DatePicker
        btnPilihTanggal.setOnClickListener {
            showDatePicker()
        }

        // Pesan button
        btnPesan.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                val intent = Intent(this, BookingActivity::class.java)
                intent.putExtra("SELECTED_DATE", selectedDate)
                intent.putExtra("SELECTED_DATE_MILLIS", selectedDateMillis)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Silakan pilih tanggal terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        // Notification button (PERBAIKAN DISINI)
        btnNotif.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("OPEN_NOTIFICATION", true)
            startActivity(intent)
        }

        // Profile button
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Callback ketika tanggal dipilih
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)
                selectedDateMillis = selectedCalendar.timeInMillis

                // Format tanggal
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                selectedDate = dateFormat.format(selectedCalendar.time)

                // Tampilkan di card
                selectedDateCard.visibility = android.view.View.VISIBLE
                tvSelectedDate.text = selectedDate

                Toast.makeText(this, "Tanggal dipilih: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )

        // Set batas minimal: hari ini (tidak bisa pilih tanggal lampau)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()

        datePickerDialog.show()
    }

    private fun setDefaultDate() {
        val calendar = Calendar.getInstance()
        selectedDateMillis = calendar.timeInMillis

        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        selectedDate = dateFormat.format(calendar.time)

        selectedDateCard.visibility = android.view.View.VISIBLE
        tvSelectedDate.text = selectedDate
    }

    fun getSelectedDate(): String {
        return selectedDate
    }

    fun getSelectedDateMillis(): Long {
        return selectedDateMillis
    }
}