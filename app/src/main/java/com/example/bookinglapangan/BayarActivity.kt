package com.example.bookinglapangan

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BayarActivity : AppCompatActivity() {

    private lateinit var txtNama: TextView
    private lateinit var txtTanggal: TextView
    private lateinit var txtJam: TextView
    private lateinit var txtHarga: TextView
    private lateinit var txtTotal: TextView

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnBayar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar)

        // Ambil ID
        txtNama = findViewById(R.id.txtBayarNama)
        txtTanggal = findViewById(R.id.txtBayarTanggal)
        txtJam = findViewById(R.id.txtBayarJam)
        txtHarga = findViewById(R.id.txtBayarHarga)
        txtTotal = findViewById(R.id.txtBayarTotal)

        radioGroup = findViewById(R.id.radioMetode)
        btnBayar = findViewById(R.id.btnBayarSekarang)

        // Dummy dari riwayat (nantinya bisa dikirim lewat Intent)
        txtNama.text = "Lapangan Futsal A"
        txtTanggal.text = "14 Nov 2025"
        txtJam.text = "09.00–10.00"
        txtHarga.text = "Rp. 75.000"
        txtTotal.text = "Rp. 75.000"

        btnBayar.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Pilih metode pembayaran dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radio: RadioButton = findViewById(selectedId)
            val metode = radio.text.toString()

            Toast.makeText(this, "Pembayaran dengan $metode berhasil!", Toast.LENGTH_LONG).show()
        }
    }
}
