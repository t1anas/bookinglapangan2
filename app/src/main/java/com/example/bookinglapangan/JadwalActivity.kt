package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

class JadwalActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var btnPesan: Button
    private lateinit var btnNotif: TextView
    private lateinit var btnProfile: TextView
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
        setupCalendarListener()
        setupButtonListeners()

        // Set default selected date to today
        setDefaultDate()
    }

    private fun initViews() {
        calendarView = findViewById(R.id.calendarView)
        btnPesan = findViewById(R.id.btnPesan)
        btnNotif = findViewById(R.id.btnNotif)
        btnProfile = findViewById(R.id.btnProfile)
        selectedDateCard = findViewById(R.id.selectedDateCard)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
    }

    private fun setupCalendarListener() {
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Format the selected date
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            selectedDateMillis = calendar.timeInMillis

            // Format date for display (e.g., "26 November 2025")
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            selectedDate = dateFormat.format(calendar.time)

            // Show selected date card
            selectedDateCard.visibility = android.view.View.VISIBLE
            tvSelectedDate.text = selectedDate

            // Show toast for feedback
            Toast.makeText(this, "Tanggal dipilih: $selectedDate", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupButtonListeners() {
        // Pesan button
        btnPesan.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                // TODO: Navigate to booking confirmation or next step
                Toast.makeText(
                    this,
                    "Melanjutkan pemesanan untuk tanggal: $selectedDate",
                    Toast.LENGTH_LONG
                ).show()

                // Example: Pass data to next activity
                 val intent = Intent(this, BookingActivity::class.java)
                 intent.putExtra("SELECTED_DATE", selectedDate)
                 intent.putExtra("SELECTED_DATE_MILLIS", selectedDateMillis)
                 startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Silakan pilih tanggal terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Notification button
        btnNotif.setOnClickListener {
            Toast.makeText(this, "Notifikasi", Toast.LENGTH_SHORT).show()

//             val intent = Intent(this, NotificationActivity::class.java)
//             startActivity(intent)
        }

        // Profile button
        btnProfile.setOnClickListener {
            Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show()

//             val intent = Intent(this, ProfileActivity::class.java)
//             startActivity(intent)
        }
    }

    private fun setDefaultDate() {
        // Set current date as default
        val calendar = Calendar.getInstance()
        selectedDateMillis = calendar.timeInMillis

        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        selectedDate = dateFormat.format(calendar.time)

        // Show selected date card with current date
        selectedDateCard.visibility = android.view.View.VISIBLE
        tvSelectedDate.text = selectedDate
    }

    // Helper function to get formatted date string
    fun getSelectedDate(): String {
        return selectedDate
    }

    // Helper function to get date in milliseconds
    fun getSelectedDateMillis(): Long {
        return selectedDateMillis
    }
}