package com.example.bookinglapangan
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PembayaranSuksesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_sukses)

        // Tampilkan pesan sukses
        findViewById<Button>(R.id.btnKembali).setOnClickListener {
            // Kembali ke halaman utama
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}

private fun Button.setOnClickListener(function: () -> Unit) {}
