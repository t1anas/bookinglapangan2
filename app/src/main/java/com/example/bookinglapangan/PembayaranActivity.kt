package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PembayaranActivity : AppCompatActivity() {

    private lateinit var imgMetode: ImageView
    private lateinit var txtMetode: TextView
    private lateinit var txtTotal: TextView
    private lateinit var txtInstruksi: TextView
    private lateinit var btnSelesai: Button
    private lateinit var btnBatal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // Inisialisasi views
        imgMetode = findViewById(R.id.imgMetodePembayaran)
        txtMetode = findViewById(R.id.txtMetodePembayaran)
        txtTotal = findViewById(R.id.txtTotalPembayaran)
        txtInstruksi = findViewById(R.id.txtInstruksi)
        btnSelesai = findViewById(R.id.btnSelesaiPembayaran)
        btnBatal = findViewById(R.id.btnBatalPembayaran)

        // Ambil data dari intent
        val metode = intent.getStringExtra("METODE") ?: "Tunai"
        val total = intent.getStringExtra("TOTAL") ?: "Rp. 0"

        // Set data
        txtMetode.text = "Pembayaran via $metode"
        txtTotal.text = total

        // Set icon dan instruksi berdasarkan metode
        when (metode) {
            "GoPay" -> {
                imgMetode.setImageResource(android.R.drawable.ic_dialog_info) // Ganti dengan icon GoPay
                txtInstruksi.text = "1. Buka aplikasi Gojek\n2. Pilih menu GoPay\n3. Scan QR Code atau masukkan kode pembayaran\n4. Konfirmasi pembayaran"
            }
            "OVO" -> {
                imgMetode.setImageResource(android.R.drawable.ic_dialog_info) // Ganti dengan icon OVO
                txtInstruksi.text = "1. Buka aplikasi OVO\n2. Pilih menu Scan\n3. Scan QR Code\n4. Masukkan PIN OVO Anda"
            }
            "Dana" -> {
                imgMetode.setImageResource(android.R.drawable.ic_dialog_info) // Ganti dengan icon Dana
                txtInstruksi.text = "1. Buka aplikasi DANA\n2. Pilih Scan QR\n3. Arahkan ke QR Code\n4. Konfirmasi dengan PIN"
            }
            "Transfer Bank" -> {
                imgMetode.setImageResource(android.R.drawable.ic_dialog_info)
                txtInstruksi.text = "Transfer ke:\nBank BCA\nNo. Rek: 1234567890\nA/N: Booking Lapangan\n\nTotal: $total"
            }
            else -> {
                imgMetode.setImageResource(android.R.drawable.ic_dialog_info)
                txtInstruksi.text = "Bayar di kasir dengan menunjukkan kode booking Anda"
            }
        }

        // Tombol selesai
        btnSelesai.setOnClickListener {
            // Simulasi proses pembayaran
            btnSelesai.isEnabled = false
            btnSelesai.text = "Memproses..."

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, PembayaranSuksesActivity::class.java)
                intent.putExtra("METODE", metode)
                intent.putExtra("TOTAL", total)
                startActivity(intent)
                finish()
            }, 2000)
        }

        btnBatal.setOnClickListener {
            finish()
        }
    }
}