package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class BayarActivity : AppCompatActivity() {

    private lateinit var txtNama: TextView
    private lateinit var txtTanggal: TextView
    private lateinit var txtJam: TextView
    private lateinit var txtHarga: TextView
    private lateinit var txtTotal: TextView

    private lateinit var iconNotification: ImageView
    private lateinit var iconProfile: ImageView

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnBayar: Button

    private var hargaValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar)

        // Inisialisasi Views
        txtNama = findViewById(R.id.txtBayarNama)
        txtTanggal = findViewById(R.id.txtBayarTanggal)
        txtJam = findViewById(R.id.txtBayarJam)
        txtHarga = findViewById(R.id.txtBayarHarga)
        txtTotal = findViewById(R.id.txtBayarTotal)

        iconNotification = findViewById(R.id.iconNotification)
        iconProfile = findViewById(R.id.iconProfile)

        radioGroup = findViewById(R.id.radioMetode)
        btnBayar = findViewById(R.id.btnBayarSekarang)

        // Ambil data dari Intent
        val namaLapangan = intent.getStringExtra("NAMA_LAPANGAN") ?: "Tidak ada data"
        val tanggal = intent.getStringExtra("TANGGAL") ?: "-"
        val jam = intent.getStringExtra("JAM") ?: "-"

        // Ambil harga sebagai Int
        hargaValue = intent.getIntExtra("HARGA", 0)

        // Jika 0, ambil sebagai String lalu parsing
        if (hargaValue == 0) {
            val hargaString = intent.getStringExtra("HARGA") ?: "0"
            hargaValue = parseHarga(hargaString)
        }

        // Format harga ke Rupiah
        val formatRupiah = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        val hargaFormatted = formatRupiah.format(hargaValue)

        // DEBUG: Cek apakah data masuk
        Log.d("BayarActivity", "Nama: $namaLapangan")
        Log.d("BayarActivity", "Tanggal: $tanggal")
        Log.d("BayarActivity", "Jam: $jam")
        Log.d("BayarActivity", "Harga: $hargaValue")

        // Tampilkan data
        txtNama.text = namaLapangan
        txtTanggal.text = tanggal
        txtJam.text = jam
        txtHarga.text = hargaFormatted
        txtTotal.text = hargaFormatted

        // Handle icon clicks
        setupIconListeners()

        // Handle tombol bayar
        btnBayar.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Pilih metode pembayaran dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radio: RadioButton = findViewById(selectedId)
            val metode = radio.text.toString()

            // Redirect ke halaman simulasi pembayaran
            val intent = Intent(this, PembayaranActivity::class.java)
            intent.putExtra("METODE", metode)
            intent.putExtra("TOTAL", hargaFormatted)
            intent.putExtra("NAMA_LAPANGAN", namaLapangan)
            intent.putExtra("TANGGAL", tanggal)
            intent.putExtra("JAM", jam)
            startActivity(intent)
        }
    }

    private fun setupIconListeners() {
        // Notification icon click
        iconNotification.setOnClickListener {
            Toast.makeText(this, "Notifikasi dibuka", Toast.LENGTH_SHORT).show()
            // Uncomment jika NotificationActivity sudah dibuat
            // val intent = Intent(this, NotificationActivity::class.java)
            // startActivity(intent)
        }

        // Profile icon click
        iconProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    // Helper function untuk parsing harga dari String
    private fun parseHarga(hargaString: String): Int {
        return try {
            // Hapus "Rp", ".", dan spasi, lalu convert ke Int
            hargaString.replace("Rp", "")
                .replace(".", "")
                .replace(",", "")
                .replace(" ", "")
                .trim()
                .toIntOrNull() ?: 0
        } catch (e: Exception) {
            0
        }
    }
}