package com.example.bookinglapangan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(view)
        setupRecyclerView(view)
        loadDummyNotifications()
    }

    private fun setupToolbar(view: View) {
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            // --- BAGIAN INI SAYA GANTI (HANYA INI) ---
            // parentFragmentManager.popBackStack() <-- HAPUS INI
            requireActivity().onBackPressed() // <-- GANTI JADI INI
        }
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewNotifications)
        emptyStateView = view.findViewById(R.id.emptyStateLayout)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun loadDummyNotifications() {
        // Data dummy notifikasi
        val notifications = listOf(
            NotificationItem(
                title = "Booking Berhasil",
                message = "Pemesanan lapangan Anda untuk tanggal 10 Des 2025 pukul 15:00 telah dikonfirmasi. Terima kasih!",
                timestamp = "2 jam yang lalu",
                isRead = false,
                iconType = "success"
            ),
            NotificationItem(
                title = "Pengingat Booking",
                message = "Jangan lupa! Anda memiliki booking besok pukul 15:00 di Griya Futsal Gravite.",
                timestamp = "5 jam yang lalu",
                isRead = false,
                iconType = "reminder"
            ),
            NotificationItem(
                title = "Pembayaran Berhasil",
                message = "Pembayaran sebesar Rp 150.000 telah berhasil diproses. Bukti pembayaran telah dikirim ke email Anda.",
                timestamp = "1 hari yang lalu",
                isRead = true,
                iconType = "payment"
            ),
            NotificationItem(
                title = "Promo Spesial Weekend",
                message = "Dapatkan diskon 20% untuk booking di hari Sabtu dan Minggu. Gunakan kode: WEEKEND20",
                timestamp = "2 hari yang lalu",
                isRead = true,
                iconType = "promo"
            ),
            NotificationItem(
                title = "Booking Dibatalkan",
                message = "Booking Anda untuk tanggal 5 Des 2025 telah dibatalkan. Refund akan diproses dalam 3-7 hari kerja.",
                timestamp = "3 hari yang lalu",
                isRead = true,
                iconType = "cancel"
            ),
            NotificationItem(
                title = "Selamat Datang!",
                message = "Terima kasih telah bergabung dengan Booking Lapangan. Selamat menikmati layanan kami!",
                timestamp = "1 minggu yang lalu",
                isRead = true,
                iconType = "info"
            )
        )

        if (notifications.isEmpty()) {
            showEmptyState()
        } else {
            showNotifications(notifications)
        }
    }

    private fun showNotifications(notifications: List<NotificationItem>) {
        recyclerView.visibility = View.VISIBLE
        emptyStateView.visibility = View.GONE

        val adapter = NotificationAdapter(notifications)
        recyclerView.adapter = adapter
    }

    private fun showEmptyState() {
        recyclerView.visibility = View.GONE
        emptyStateView.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}

// Data class untuk Notification Item
data class NotificationItem(
    val title: String,
    val message: String,
    val timestamp: String,
    val isRead: Boolean,
    val iconType: String
)